'use strict';

/* App Module */

angular.module('phonecat', ['phonecatFilters', 'phonecatServices']).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/phones', {templateUrl: 'partials/phone-list.html',   controller: PhoneListCtrl}).
      when('/phones/:phoneId', {templateUrl: 'partials/phone-detail.html', controller: PhoneDetailCtrl}).
      when('/permissions', {templateUrl: 'partials/permissions.html',   controller: PermissionController}).
      when('/profiles', {templateUrl: 'partials/profiles.html',   controller: ProfileController}).
      when('/permcreate', {templateUrl: 'partials/createpermission.html',   controller: CreatePermissionController}).
      otherwise({redirectTo: '/permissions'});
}]);
