/*global define*/
/*global console*/
/*global require*/
define([], function () {
    'use strict';
    return function (dependencies) {
        console.log('dependencies: ' + dependencies);
        var definition = {
            resolver: ['$q', '$rootScope', function ($q, $rootScope) {
                var deferred = $q.defer();
                require(dependencies, function () {
                    $rootScope.$apply(function () {
                        console.log('promise resolved');
                        deferred.resolve();
                    });
                });
                return deferred.promise;
            }]
        };
        return definition;
    };
});