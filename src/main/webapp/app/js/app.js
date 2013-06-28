/*global define*/
/*global console*/
define([
    'angular',
    'dependencyResolverFor',
    'ngResource',
    'uiBootstrap',
    'filters',
    'services',
    'directives',
    'directives/DisclosurePanel',
    'directives/ProgressBar',
    'services/PermissionsFactory',
    'controllers',
    'controllers/HomeController',
    'controllers/PermissionController',
    'controllers/CreatePermissionController',
    'controllers/ProfileController',
    'controllers/WorkgroupController',
    'controllers/WorkgroupDetailsController',
    'controllers/WorkgroupCreateController',
    'controllers/PermissionUsersController'],
    function(angular) {
        'use strict';
        var appModule = angular.module('cuaApp',
            [
                'ngResource',
                'ui.bootstrap',
                'cuaApp.controllers',
                'cuaApp.filters',
                'cuaApp.services',
                'cuaApp.directives'
            ]
        );
        appModule.config(
            [
                '$routeProvider',
                '$locationProvider',
                '$controllerProvider',
                '$compileProvider',
                '$filterProvider',
                function(
                    $routeProvider,
                    $locationProvider,
                    $controllerProvider,
                    $compileProvider,
                    $filterProvider)
                {
                    appModule.lazy = {
                        controller: $controllerProvider.register,
                        directive: $compileProvider.directive,
                        filter: $filterProvider.register
                };
                    $routeProvider
                        .when('/home', {
                            templateUrl: 'app/partials/home.html',
                            controller: 'HomeController'})
                        .when('/permissions', {
                            templateUrl: 'app/partials/permissions.html',
                            controller: 'PermissionController'})
                        .when('/profiles', {
                            templateUrl: 'app/partials/profiles.html',
                            controller: 'ProfileController'})
                        .when('/permcreate', {
                            templateUrl: 'app/partials/createpermission.html',
                            controller: 'CreatePermissionController'})
                        .when('/workgroups', {
                            templateUrl: 'app/partials/workgroups.html',
                            controller: 'WorkgroupController'})
                        .when('/workgroup/:workgroupID', {
                            templateUrl: 'app/partials/workgroupdetails.html',
                            controller: 'WorkgroupDetailsController'})
                        .when('/workgroupcreate', {
                            templateUrl: 'app/partials/workgroupcreate.html',
                            controller: 'WorkgroupCreateController'})
                        .when('/permusersreport', {
                            templateUrl: 'app/partials/permusersreport.html',
                            controller: 'PermissionUsersController'})
                        .when('/customdirective', {
                            templateUrl: 'app/partials/directives.html',
                            controller: 'HomeController'})
                        .otherwise({
                            redirectTo: '/home'
                        });
                }]);
        return appModule;
    });
