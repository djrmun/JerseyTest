/*global define*/
/*global console*/
/*global require*/

define(['services', 'ngResource'], function (services, ngResource) {
    'use strict';
    services.factory('PermissionsFactory', ['$http', '$q', '$resource', function ($http, $q, $resource) {
        var baseUrl = '/JerseyTest/rest/ldap/permission';
        return {
            get: function () {
                /*
                var deferred = $q.defer();
                $http
                    .get(baseUrl + '/all')
                    .success(function (data) {
                        deferred.resolve(data);
                    })
                    .error(function (data) {
                        deferred.reject(data);
                    });
                return deferred.promise;
                */
                return $http.get(baseUrl + '/all');
            },
            save: function (newPermission) {
                return $http.post(baseUrl + '/create', newPermission);
            }
        };
    }]);
});