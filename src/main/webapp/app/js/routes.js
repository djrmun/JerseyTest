define(['angular', 'app', 'dependencyResolverFor'], function(angular, appModule, dependencyResolverFor) {
	'use strict';

	return console.log('Configuring Routeprovider for lazy resolving of conrtrollers: '+appModule);
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
					resolve : dependencyResolverFor(['controllers/PermissionController'])
				}).when('/profiles', {
					templateUrl : 'app/partials/profiles.html',
					resolve : dependencyResolverFor(['controllers/ProfileController'])
				}).when('/permcreate', {
					templateUrl : 'app/partials/createpermission.html',
					resolve : dependencyResolverFor(['controllers/CreatePermissionController'])
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

});