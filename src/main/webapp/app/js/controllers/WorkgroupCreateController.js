/*global define*/
/*global console*/
/*global require*/

define(['angular', 'controllers'], function (angular, controllers) {

    'use strict';

    controllers.controller('WorkgroupCreateController', ['$scope', '$http', function ($scope, $http) {
        $scope.master = {};
        var allpermissions = [];
        $scope.allPermissionsFetchUrl = '/JerseyTest/rest/ldap/permission/all';

        function search() {
            $http
                .get($scope.allPermissionsFetchUrl).success(function (data, status) {
                    $scope.status = status;
                    $scope.permissions = data;
                    allpermissions = data;
                    $scope.errorMsg = "success fetching permissions!!" + status;
                })
                .error(function (data, status) {
                    $scope.errorMsg = "No Doughnut for you :( Permissions Fetch Failed" + status;
                    $scope.permissions = [];
                    $scope.data = data || "Request failed";
                    $scope.status = status;
                });
        }

        search();
        $scope.workgroup = {};
        $scope.workgroupPermissions = [];
        $scope.workgroup.cuaPermissionName = [];

        $scope.create = function (workgroup) {
            $scope.master = angular.copy(workgroup);
            $http
                .post('http://localhost:8080/JerseyTest/rest/ldap/profile/create', $scope.master)
                .success(function (data, status) {
                    $scope.errorMsg = "success " + status;
                })
                .error(function (data, status) {
                    $scope.errorMsg = "No Doughnut for you :) " + status;
                });
        };

        function setWorkGroupPermissionsInTheModel() {
            var permissionsArray = [];
            $scope.workgroup.cuaPermissionName = [];
            $scope.workgroupPermissions.forEach(function (item) {
                permissionsArray.push(item.cuaPermissionName);
            });
            $scope.workgroup.cuaPermissionName = permissionsArray;
        }

        $scope.addToWorkGroupPermissions = function (permissionsToBeAdded) {
            var i = 0;
            if (permissionsToBeAdded) {
                $scope.workgroupPermissions.push.apply($scope.workgroupPermissions, permissionsToBeAdded);
                permissionsToBeAdded.forEach(function (item) {
                    for (i = $scope.permissions.length - 1; i >= 0; i -= 1) {
                        if ($scope.permissions[i].cuaPermissionName === item.cuaPermissionName) {
                            $scope.permissions.splice(i, 1);
                        }
                    }
                });
                console.log($scope.permissions.length);
                console.log($scope.workgroupPermissions.length);
            }
            setWorkGroupPermissionsInTheModel();
        };

        $scope.removeFromWorkGroupPermissions = function (permissionsToBeRemoved) {
            var i = 0;
            if (permissionsToBeRemoved) {
                $scope.permissions.push.apply($scope.permissions, permissionsToBeRemoved);
                permissionsToBeRemoved.forEach(function (item) {
                    for (i = $scope.workgroupPermissions.length - 1; i >= 0; i -= 1) {
                        if ($scope.workgroupPermissions[i].cuaPermissionName === item.cuaPermissionName) {
                            $scope.workgroupPermissions.splice(i, 1);
                        }
                    }
                });
                console.log($scope.permissions.length);
                console.log($scope.workgroupPermissions.length);
            }
            setWorkGroupPermissionsInTheModel();
        };

        $scope.reset = function () {
            $scope.perm = '';
        };
    }]);
});