/*global define*/
/*global console*/
/*global require*/
define(['controllers'], function(controllers) {
    'use strict';
    controllers.controller('PermissionController',
        ['$scope', '$http', 'PermissionsFactory',
            function($scope, $http, PermissionsFactory) {
                var promise = PermissionsFactory.get();
                $scope.text = 'Lorem Ipsum Blah Blah';
                $scope.title = 'Click me to expand';
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
