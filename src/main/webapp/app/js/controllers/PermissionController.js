/*global define*/
/*global console*/
/*global require*/
define(['controllers'], function(controllers) {
    'use strict';
    controllers.controller('PermissionController',
        ['$scope', '$http', 'PermissionsFactory',
            function($scope, $http, PermissionsFactory) {
                var promise = PermissionsFactory.get();
                promise
                    .then(
                    function(result) {
                        $scope.permissions = result.data;
                    },
                    function(result) {
                        console.log(result);
                        $scope.permissions = [];
                    }
                );
            }]);
});
