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
                promise
                    .then(
                    function(result) {
                        $scope.permissions = result.data;
                        $scope.errorMsg =
                            'Successfully fetched all permissions:' + result.status;
                    },
                    function(result) {
                        $scope.permissions = [];
                        $scope.errorMsg =
                            'Failed to fetch permissions No Doughnut for you :( ';
                    }
                );
                $scope.update = function() {
                $http
                    .get(baseUrl + $scope.selectedPermission.cuaPermissionName)
                    .success(
                    function(data, status) {
                        $scope.errorMsg = 'Successfully fetched users :)' + status;
                        $scope.users = _.map(data, function(user, key) {
                            return user;
                        });
                    })
                    .error(function(data, status) {
                        $scope.errorMsg = "No Doughnut for you :( " + status;
                    });
                };
            }]);
});
