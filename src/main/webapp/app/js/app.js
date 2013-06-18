define([
	'angular',
	'dependencyResolverFor',
	'angularResource',
	'filters',
	'services',
	'directives',
	'controllers',
	'controllers/PermissionController',
	'controllers/CreatePermissionController',
	'controllers/ProfileController',
	'controllers/WorkgroupController',
	'controllers/WorkgroupDetailsController',
	'controllers/WorkgroupCreateController'
	], function (angular, dependencyResolverFor) {
		'use strict';
		console.log('Creating Application Module cuaApp');
		var appModule =  angular.module('cuaApp', ['ngResource','cuaApp.controllers', 'cuaApp.filters', 'cuaApp.services', 'cuaApp.directives']);

		console.log('Configuring Routeprovider for lazy resolving of conrtrollers');
		appModule.config(['$routeProvider', '$locationProvider', '$controllerProvider', '$compileProvider', '$filterProvider', 
			function($routeProvider, $locationProvider, $controllerProvider, $compileProvider, $filterProvider) {
				appModule.lazy =
				{
                	controller : $controllerProvider.register,
                	directive  : $compileProvider.directive,
                	filter     : $filterProvider.register
                };

                $routeProvider.when('/home', {
					templateUrl : 'app/partials/home.html',
					controller : 'PermissionController'
				}).when('/permissions', {
					templateUrl : 'app/partials/permissions.html',
					controller : 'PermissionController'
				}).when('/profiles', {
					templateUrl : 'app/partials/profiles.html',
					controller : 'ProfileController'
				}).when('/permcreate', {
					templateUrl : 'app/partials/createpermission.html',
					controller : 'CreatePermissionController'
				}).when('/workgroups',{
					templateUrl : 'app/partials/workgroups.html',
					controller : 'WorkgroupController'
				}).when('/workgroup/:workgroupID',{
					templateUrl : 'app/partials/workgroupdetails.html',
					controller : 'WorkgroupDetailsController'
				}).when('/workgroupcreate',{
					templateUrl : 'app/partials/workgroupcreate.html',
					controller : 'WorkgroupCreateController'
				}).otherwise({
					redirectTo : '/home'
				});

			}]);

		return appModule;
});
