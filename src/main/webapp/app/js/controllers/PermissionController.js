/*global define*/
/*global console*/
/*global require*/
define(['controllers', 'services', 'services/PermissionsFactory'], function (controllers, services, PermissionsFactory) {
    'use strict';
    controllers.controller('PermissionController', ['$scope', '$http', 'PermissionsFactory',
        function ($scope, $http, PermissionsFactory) {
            $scope.permissions = PermissionsFactory.get();
        }]);
});