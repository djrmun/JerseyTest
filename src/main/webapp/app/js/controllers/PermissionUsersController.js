/*global define*/
/*global console*/
/*global require*/

define(['controllers', 'services'], function (controllers, services) {
    'use strict';
    controllers.controller('PermissionUsersController', ['$scope', '$http', 'PermissionsFactory',
        function ($scope, $http, PermissionsFactory) {
            $scope.url = '/JerseyTest/rest/ldap/permission/all';
            function init() {
                console.log('search invoked');
                $http
                    .get($scope.url).success(function (data, status) {
                        $scope.status = status;
                        $scope.permissions = data;
                        $scope.errorMsg = "Successfully fetched all permissions :) " + status;
                    })
                    .error(function (data, status) {
                        $scope.data = data || "Fetching to fetch permissions!!! No Doughnut for you :( ";
                        $scope.status = status;
                    });
            }

            $scope.permissions = PermissionsFactory.get();
            $scope.update = function () {
                $http.
                    get('http://localhost:8080/JerseyTest/rest/ldap/user/findUsersWithPermission/' + $scope.selectedPermission.cuaPermissionName)
                    .success(function (data, status) {
                        $scope.errorMsg = "Successfully fetched users :) " + status;
                        $scope.users = _.map(data, function (user, key) {
                            return user;
                        });
                        console.log($scope.users);
                    })
                    .error(function (data, status) {
                        $scope.errorMsg = "No Doughnut for you :( " + status;
                    });
            };
        }]);
});