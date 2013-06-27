/*global define*/
/*global console*/
/*global require*/

define(['controllers', 'services'], function(controllers, services) {
    'use strict';
    controllers.controller('PermissionUsersController',
        ['$scope', '$http', 'PermissionsFactory',
            function($scope, $http, PermissionsFactory) {
                var baseUrl =
                    '/JerseyTest/rest/ldap/user/findUsersWithPermission/',
                    promise = PermissionsFactory.get();

                $scope.alerts = [];
                $scope.showProgressBar = false;

                promise
                    .then(
                    function(result) {
                        $scope.permissions = result.data;
                        $scope.alerts.push(
                            {
                                type: 'success',
                                msg: 'Successfully fetched all permissions:' + result.status
                            }
                        );
                    },
                    function(result) {
                        $scope.permissions = [];
                        $scope.alerts.push(
                            {
                                type: 'error',
                                msg: 'Failed to fetch permissions No Doughnut for you :( '
                            }
                        );
                    }
                );

                $scope.closeAlert = function(index) {
                    $scope.alerts.splice(index, 1);
                };

                $scope.update = function() {
                    $scope.showProgressBar = true;
                    $http
                        .get(baseUrl + $scope.selectedPermission.cuaPermissionName)
                        .success(
                        function(data, status) {
                            $scope.alerts.push(
                                {
                                    type: 'success',
                                    msg: 'Successfully fetched users :)' + status
                                }
                            );
                            $scope.users = _.map(data, function(user, key) {
                                return user;
                            });
                            $scope.showProgressBar = false;
                        })
                        .error(function(data, status) {
                            $scope.alerts.push(
                                {
                                    type: 'error',
                                    msg: 'No Doughnut for you :(' + status
                                }
                            );
                            $scope.showProgressBar = false;
                        });
                    };
            }]);
});
