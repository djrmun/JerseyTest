/*global define*/
/*global console*/
/*global require*/
define(['controllers'], function(controllers) {
    'use strict';
    controllers.controller('HomeController',
        ['$scope',
            function($scope) {
                $scope.text = 'Lorem Ipsum Blah Blah';
                $scope.title = 'Click me to expand';
            }]);
});
