'use strict';

define(['controllers','services'], function(controllers,services) {

	controllers.controller('CreatePermissionController', ['$scope', '$http', function($scope, $http) {

		$scope.perm = {
			cuaPermissionName: '',
			description : '',
			cuaIsPortSwapPermission : false ,
			cuaEntityIsAdmin : false ,
			cuaPermissionCategory : [' ',' ']
		};

		console.log($scope.perm);

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
				if($scope.perm.cuaPermissionCategory)
					$scope.perm.cuaPermissionCategory[0] = newValue.name;
				else
					console.log("permissions category array not defined");
			}else{
				console.log("newvalue is undefined");
			}		
		};

		$scope.$watch('selectedDomain',updatePermWithDomain);

		function updatePermWithType(newValue,oldValue){
			if(newValue){
				if($scope.perm.cuaPermissionCategory)
					$scope.perm.cuaPermissionCategory[1] = newValue.name;
				else
					console.log("permissions category type not definesd");
			}else{
				console.log("new type is undefrined");
			}
			
			console.log("updatePermWithType invoked");
		};

		$scope.$watch('selectedType',updatePermWithType);

		$scope.master = {};

		$scope.update = function(perm) {
			$scope.master = angular.copy(perm);
			$http.
				post('http://localhost:8080/JerseyTest/rest/ldap/permission/create',$scope.master)
					.success(function(data, status) {
						$scope.errorMsg = "success " + status;
					})
					.error(function(data, status) {
						$scope.errorMsg = "No Doughnut for you :) " + status;
				});
		};

		$scope.reset = function() {
			$scope.perm = {
				cuaPermissionName: '',
				description : '',
				cuaIsPortSwapPermission : false ,
				cuaEntityIsAdmin : false ,
				cuaPermissionCategory : [' ',' ']
			};
		};

	}]);
});