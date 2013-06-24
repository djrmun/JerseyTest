/*global define*/
/*global console*/
/*global require*/

define(['controllers'], function (controllers) {
    'use strict';
    controllers.controller('WorkgroupController', ['$scope', '$http', function ($scope, $http) {
        $scope.order = 'cuaProfileName';
        $scope.url = '/JerseyTest/rest/ldap/profile/all';
        $scope.search = function () {
            $http.get($scope.url)
                .success(function (data, status) {
                    $scope.status = status;
                    $scope.workgroups = data;
                })
                .error(function (data, status) {
                    $scope.data = data || "Request failed";
                    $scope.status = status;
                });
        };
        $scope.search();
    }]);
});