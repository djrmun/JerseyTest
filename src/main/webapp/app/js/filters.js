/*global define*/
define(['angular', 'services'], function (angular, services) {
    'use strict';
    Array.prototype.getUnique = function () {
        var u = {}, unique = [], i = 0, domainprefix = '';
        for (i = 0; i < this.length; i += 1) {
            domainprefix = this[i].split('.')[0];
            if (domainprefix === "commontopology") {
                domainprefix = "serviceorder";
            }
            if (domainprefix === "alert") {
                domainprefix = "serviceorder";
            }
            if (domainprefix === "canned") {
                domainprefix = "reports";
            }
            if (domainprefix === "mediation") {
                domainprefix = "reports";
            }
            if (!u.hasOwnProperty(domainprefix)) {
                unique.push(domainprefix);
                u[domainprefix] = 1;
            }
        }

        return unique;
    };

    angular.module('cuaApp.filters', ['cuaApp.services'])
        .filter('interpolate', ['version', function (version) {
            return function (text) {
                return String(text).replace(/\%VERSION\%/mg, version);
            };
        }])
        .filter('checkmark', function () {
            return function (input) {
                return input ? '\u2713' : '\u2718';
            };
        })
        .filter('sortAlphabetically', function () {
            return function (input) {
                if (input) {
                    return input.sort();
                }
                return input;
            };
        })
        .filter('extractDomainsForPermissions', function () {
            return function (input) {
                if (input) {
                    return input.getUnique();
                }
                return input;
            };
        });
});
