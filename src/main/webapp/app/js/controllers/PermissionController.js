'use strict';

define(['controllers','services'], function(controllers,services) {

	controllers.controller('PermissionController', ['$scope', '$http', function($scope, $http) {

	$scope.url = 'http://localhost:8080/JerseyTest/rest/ldap/permission/all';

	$scope.search = function() {
		console.log('search invoked');
		$http.get($scope.url).success(function(data, status) {
			$scope.status = status;
			$scope.permissions = data;

		}).error(function(data, status) {
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};

	$scope.search();

	}]);
});