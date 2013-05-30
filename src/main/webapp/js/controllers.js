'use strict';

/* Controllers */

function PhoneListCtrl($scope, Phone) {
	$scope.phones = Phone.query();
	$scope.orderProp = 'age';
}

// PhoneListCtrl.$inject = ['$scope', 'Phone'];

function PhoneDetailCtrl($scope, $routeParams, Phone) {
	$scope.phone = Phone.get({
		phoneId : $routeParams.phoneId
	}, function(phone) {
		$scope.mainImageUrl = phone.images[0];
	});

	$scope.setImage = function(imageUrl) {
		$scope.mainImageUrl = imageUrl;
	}
}

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
