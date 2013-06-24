/*global define*/
/*global console*/
/*global require*/
define(['angular', 'controllers'], function (angular, controllers) {
    'use strict';
    controllers.controller('CreatePermissionController', ['$scope', '$http', 'PermissionsFactory', function ($scope, $http, PermissionsFactory) {
        $scope.perm = {
            cuaPermissionName: '',
            description : '',
            cuaIsPortSwapPermission : false,
            cuaEntityIsAdmin : false,
            cuaPermissionCategory : [' ', ' ']
        };

        console.log($scope.perm);

        $scope.domains = [ {
            name : 'domain_serviceorder'
        }, {
            name : 'domain_subscription'
        }, {
            name : 'domain_admin'
        } ];

        $scope.types = [ {
            name : 'type_permission'
        }, {
            name : 'type_nti'
        } ];

        function updatePermWithDomain(newValue, oldValue) {
            console.log("updatePermWithDomain invoked");
            if (newValue) {
                if ($scope.perm.cuaPermissionCategory) {
                    $scope.perm.cuaPermissionCategory[0] = newValue.name;
                }
            } else {
                console.log("new value is undefined");
            }
        }

        $scope.$watch('selectedDomain', updatePermWithDomain);

        function updatePermWithType(newValue, oldValue) {
            if (newValue) {
                if ($scope.perm.cuaPermissionCategory) {
                    $scope.perm.cuaPermissionCategory[1] = newValue.name;
                } else {
                    console.log("permissions category type not defined");
                }
            } else {
                console.log("new type is undefined");
            }
        }

        $scope.$watch('selectedType', updatePermWithType);

        $scope.master = {};

        $scope.update = function (perm) {
            angular.copy(perm, $scope.master);

            var promise = PermissionsFactory.save($scope.master);
            promise.then(function (result) {
                $scope.errorMsg = "success " + result.status;
                console.log(result);
            }, function (result) {
                $scope.errorMsg = "No Doughnut for you :) " + result.status;
                console.log(result);
            });
        };

        $scope.reset = function () {
            $scope.perm = {
                cuaPermissionName: '',
                description : '',
                cuaIsPortSwapPermission : false,
                cuaEntityIsAdmin : false,
                cuaPermissionCategory : [' ', ' ']
            };
        };

    }]);
});