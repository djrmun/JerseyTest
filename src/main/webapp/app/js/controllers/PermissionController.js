'use strict';

define(['controllers','services','services/PermissionsFactory'], function(controllers,services,PermissionsFactory) {

	controllers.controller('PermissionController', ['$scope', '$http', 'PermissionsFactory', 
		function($scope, $http, PermissionsFactory) {

			$scope.permissions = PermissionsFactory.get();

	}]);
});