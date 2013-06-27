/*global define*/
/*global console*/

define(['controllers'], function(controllers) {
    'use strict';
    controllers.controller('WorkgroupDetailsController',
        ['$scope', '$http', '$routeParams',
            function($scope, $http, $routeParams) {
                var baseUrl = '/JerseyTest/rest/ldap/profile/';
                $scope.url = baseUrl + $routeParams.workgroupID;
                $http
                    .get($scope.url)
                    .success(function(data, status) {
                        $scope.status = status;
                        $scope.workgroup = data;
                        console.log($scope.workgroup.cuaPermissionName);
                    })
                    .error(function(data, status) {
                        $scope.data = data || 'Request failed';
                        $scope.status = status;
                    });
            }]);
});
