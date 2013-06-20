'use strict';

define(['controllers','services'], function(controllers,services) {

	controllers.controller('WorkgroupDetailsController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

	$scope.url = '/JerseyTest/rest/ldap/profile/' + $routeParams.workgroupID;

	$http.get($scope.url).success(function(data, status) {
		$scope.status = status;
		$scope.workgroup = data;
		console.log($scope.workgroup.cuaPermissionName)
	}).error(function(data, status) {
		$scope.data = data || "Request failed";
		$scope.status = status;
	});
	
	}]);
});