'use strict';

/* Controllers */

function PhoneListCtrl($scope, Phone) {
  $scope.phones = Phone.query();
  $scope.orderProp = 'age';
}

//PhoneListCtrl.$inject = ['$scope', 'Phone'];

function PhoneDetailCtrl($scope, $routeParams, Phone) {
  $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
    $scope.mainImageUrl = phone.images[0];
  });

  $scope.setImage = function(imageUrl) {
    $scope.mainImageUrl = imageUrl;
  }
}

function PermissionController($scope,$http){
      $scope.url = 'http://localhost:8080/JerseyTest/rest/ldap/permission/all';

      $scope.search = function() {
        $http.get($scope.url).
        success(function(data,status){
          $scope.status = status;
          $scope.permissions = data;

        }).
        error(function(data,status){
          $scope.data = data || "Request failed";
          $scope.status=status;
        });
      };    
  }

function CreatePermissionController($scope,$http){
	 $scope.master= {};
	 
	 $scope.update = function(perm) {
		 $scope.master= angular.copy(perm);
	 };
	  
	 $scope.reset = function() {
		 $scope.perm = angular.copy($scope.master);
	 };
	  
	 $scope.reset();
}

//PhoneDetailCtrl.$inject = ['$scope', '$routeParams', 'Phone'];
