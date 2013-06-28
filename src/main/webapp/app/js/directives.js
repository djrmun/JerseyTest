/*global define*/
define(['angular', 'services'], function(angular, services) {
    'use strict';
    var directivesModule = angular.module('cuaApp.directives',
        ['cuaApp.services']);
    directivesModule.directive('appVersion', ['version', function(version) {
        return function(scope, elm, attrs) {
            elm.text(version);
        };
    }]);

    return directivesModule;
});
