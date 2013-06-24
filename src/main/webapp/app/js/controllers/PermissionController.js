/*global define*/
/*global console*/
/*global require*/
define(['controllers', 'services/PermissionsFactory'], function (controllers, PermissionsFactory) {
    'use strict';
    controllers.controller('PermissionController', ['$scope', '$http', 'PermissionsFactory',
        function ($scope, $http, PermissionsFactory) {
            var promise = PermissionsFactory.get();
            promise.then(function (data) {
                $scope.permissions = data;
            }, function (data) {
                console.log(data);
                $scope.permissions = [];
            });

        }]);
});