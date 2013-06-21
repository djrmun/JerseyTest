/*global define*/
/*global console*/
/*global require*/

define(['services', 'angular', 'ngResource'], function (services, angular, ngResource) {
    'use strict';
    services.factory('PermissionsFactory', ['$http', '$resource', function ($http, $resource) {
        return $resource('/JerseyTest/rest/ldap/permission/all', {}, {
            get: {method: 'GET', isArray : true}
        });
    }]);
});