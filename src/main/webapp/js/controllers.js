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

	$scope.search();
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
		$scope.perm = '';
	};

	$scope.reset();
}

function WorkgroupController($scope, $http) {

	$scope.order = 'cuaProfileName';
	$scope.url = 'http://localhost:8080/JerseyTest/rest/ldap/profile/all';

	$scope.search = function() {
		$http.get($scope.url).success(function(data, status) {
			$scope.status = status;
			$scope.workgroups = data;

		}).error(function(data, status) {
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};

	$scope.search();
}

function WorkgroupDetailsController($scope, $http, $routeParams) {
	$scope.url = 'http://localhost:8080/JerseyTest/rest/ldap/profile/'+$routeParams.workgroupID;

	$http.get($scope.url).success(function(data, status) {
		$scope.status = status;
		$scope.workgroup = data;
		console.log($scope.workgroup.cuaPermissionName)
	}).error(function(data, status) {
		$scope.data = data || "Request failed";
		$scope.status = status;
	});
}

function WorkgroupCreateController($scope, $http) {

	$scope.master = {};

	var allpermissions = [];

	$scope.allPermissionsFetchUrl = 'http://localhost:8080/JerseyTest/rest/ldap/permission/all';

	function search() {
		$http.get($scope.allPermissionsFetchUrl).success(function(data, status) {
			$scope.status = status;
			$scope.permissions = data;
			allpermissions = data;
			$scope.errorMsg = "success fetching permissions!!" + status;
		}).error(function(data, status) {
			$scope.errorMsg = "No Doughnut for you :( Permissions Fetch Failed" + status;
			$scope.permissions = [];
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};

	search();

	$scope.workgroup = {};
	$scope.workgroupPermissions = [];
	$scope.workgroup.cuaPermissionName = [];

	$scope.create = function(workgroup) {
		$scope.master = angular.copy(workgroup);
		$http.post('http://localhost:8080/JerseyTest/rest/ldap/profile/create',$scope.master)
		.success(function(data, status) {
			$scope.errorMsg = "success " + status;
		}).error(function(data, status) {
			$scope.errorMsg = "No Doughnut for you :) " + status;
		});
	};

	$scope.addToWorkGroupPermissions = function(permissionsToBeAdded) {
		if(permissionsToBeAdded){
			$scope.workgroupPermissions.push.apply($scope.workgroupPermissions,permissionsToBeAdded);

			permissionsToBeAdded.forEach(function(item){
				for (var i = $scope.permissions.length - 1; i >= 0; i--) {
					if($scope.permissions[i].cuaPermissionName === item.cuaPermissionName){
						$scope.permissions.splice(i,1);
					}
				};
			});
			console.log($scope.permissions.length);
			console.log($scope.workgroupPermissions.length);
		}
		setWorkGroupPermissionsInTheModel();
	};

	$scope.removeFromWorkGroupPermissions = function(permissionsToBeRemoved) {
		if(permissionsToBeRemoved){
			
			$scope.permissions.push.apply($scope.permissions,permissionsToBeRemoved);		

			permissionsToBeRemoved.forEach(function(item){
				for (var i = $scope.workgroupPermissions.length - 1; i >= 0; i--) {
					if($scope.workgroupPermissions[i].cuaPermissionName === item.cuaPermissionName){
						$scope.workgroupPermissions.splice(i,1);
					}
				};

			});
			console.log($scope.permissions.length);
			console.log($scope.workgroupPermissions.length);
		}

		setWorkGroupPermissionsInTheModel();
	};

	function setWorkGroupPermissionsInTheModel(){
		var permissionsArray = [];
		$scope.workgroup.cuaPermissionName = [];
		$scope.workgroupPermissions.forEach(function(item){
			permissionsArray.push(item.cuaPermissionName);
		});
		$scope.workgroup.cuaPermissionName = permissionsArray;
	};

	$scope.reset = function() {
		$scope.perm = '';
	};

	$scope.reset();
}