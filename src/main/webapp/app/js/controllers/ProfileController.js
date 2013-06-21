/*global define*/
/*global console*/
/*global require*/

define(['controllers', 'services'], function (controllers, services) {
    'use strict';
    controllers.controller('ProfileController', ['$scope', '$http', function ($scope, $http) {
        $scope.url = '/JerseyTest/rest/ldap/profile/all';

        $scope.search = function () {
            $http
                .get($scope.url)
                .success(function (data, status) {
                    $scope.status = status;
                    $scope.profiles = data;
                })
                .error(function (data, status) {
                    $scope.data = data || "Request failed";
                    $scope.status = status;
                });
        };
    }]);
});