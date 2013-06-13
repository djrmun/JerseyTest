'use strict';

/* App Module */

angular.module('cuaApp', [ 'phonecatFilters', 'phonecatServices' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/home', {
				templateUrl : 'partials/home.html',
				controller : PermissionController
			}).when('/permissions', {
				templateUrl : 'partials/permissions.html',
				controller : PermissionController
			}).when('/profiles', {
				templateUrl : 'partials/profiles.html',
				controller : ProfileController
			}).when('/permcreate', {
				templateUrl : 'partials/createpermission.html',
				controller : CreatePermissionController
			}).when('/workgroups',{
				templateUrl : 'partials/workgroups.html',
				controller : WorkgroupController
			}).when('/workgroup/:workgroupID',{
				templateUrl : 'partials/workgroupdetails.html',
				controller : WorkgroupDetailsController
			}).when('/workgroupcreate',{
				templateUrl : 'partials/workgroupcreate.html',
				controller : WorkgroupCreateController
			}).otherwise({
				redirectTo : '/home'
			});
		} ]);
