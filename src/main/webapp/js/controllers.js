'use strict';

/* Controllers */

function PermissionController($scope, $http) {
	$scope.url = 'http://localhost:8080/JerseyTest/rest/ldap/permission/all';

	$scope.search = function() {
		$http.get($scope.url).success(function(data, status) {
			$scope.status = status;
			$scope.permissions = data;

		}).error(function(data, status) {
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};
}

function ProfileController($scope, $http) {
	$scope.url = 'http://localhost:8080/JerseyTest/rest/ldap/profile/all';

	$scope.search = function() {
		$http.get($scope.url).success(function(data, status) {
			$scope.status = status;
			$scope.profiles = data;

		}).error(function(data, status) {
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};
}

function CreatePermissionController($scope, $http) {

	var cuaPermissionCategory = [];
	$scope.perm = new Object();
	$scope.perm.cuaIsPortSwapPermission = false;
	$scope.perm.cuaEntityIsAdmin = false;

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

	function updatePermWithDomain(newValue,oldValue){
		console.log("updatePermWithDomain invoked");
		if(newValue){
			cuaPermissionCategory[0] = newValue.name;
		}else{
			console.log("newvalue is undefined");
		}
		
		
		$scope.perm.cuaPermissionCategory = cuaPermissionCategory;		
	};

	$scope.$watch('selectedDomain',updatePermWithDomain);

	function updatePermWithType(newValue,oldValue){
		if(newValue){
			cuaPermissionCategory[1] = newValue.name;
		}else{
			console.log("new type is undefrined");
		}
		
		console.log("updatePermWithType invoked");
		$scope.perm.cuaPermissionCategory = cuaPermissionCategory;		
	};

	$scope.$watch('selectedType',updatePermWithType);

	$scope.master = {};

	$scope.update = function(perm) {
		$scope.master = angular.copy(perm);
		$http.post(
				'http://localhost:8080/JerseyTest/rest/ldap/permission/create',
				$scope.master).success(function(data, status) {
			$scope.errorMsg = "success " + status;
		}).error(function(data, status) {
			$scope.errorMsg = "No Doughnut for you :) " + status;
		});
	};

	$scope.reset = function() {
		$scope.perm = angular.copy($scope.master);
	};

	$scope.reset();
}