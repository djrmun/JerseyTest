/**
 * Created with JetBrains WebStorm.
 * User: va2839
 * Date: 6/28/13
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */

/*global define*/
/*global console*/
/*global require*/

define(['directives'], function(directives) {
    'use strict';
    directives.directive('expander', function() {
        return {
            restrict: 'E,A',
            replace: true,
            transclude: true,
            template: '<div>' +
                '<div class="title" ng-click="toggle()">{{ title }}</div>' +
                '<div class="body" ng-show="showMe" ng-transclude></div>' +
                '</div>',
            link: function(scope, element, attrs) {
                scope.showMe = false;

                scope.toggle = function() {
                    scope.showMe = !scope.showMe;
                };
            }
        };
    });
});


