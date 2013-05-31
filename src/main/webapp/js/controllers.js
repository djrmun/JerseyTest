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

	$scope.master = {};

	$scope.update = function(perm) {
		var arr = [];
		arr.push($scope.selectedDomain);
		arr.push($scope.selectedType);
		$scope.perm.cuaPermissionCategory = arr;
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

// PhoneDetailCtrl.$inject = ['$scope', '$routeParams', 'Phone'];
