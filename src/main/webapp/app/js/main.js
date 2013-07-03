/*global require*/
/*global console*/
/*global document*/
require.config({
    paths: {
        jquery:
            [
                'http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min',
                'lib/jquery/jquery'
            ],
        json3:
            [
                'http://cdnjs.cloudflare.com/ajax/libs/json3/3.2.4/json3.min',
                'lib/json3/json3'
            ],
        angular:
            [
                'http://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min',
                'lib/angular/angular'
            ],
        uiBootstrap:
            [
                'lib/angular-ui/ui-bootstrap-tpls-0.4.0.min',
                'lib/angular-ui/ui-bootstrap--tpls-0.4.0'
            ],
        ngResource:
            [
                'http://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular-resource.min',
                'lib/angular/angular-resource'
            ],
        twitter:
            [
                'http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min',
                'lib/bootstrap/bootstrap'
            ],
        underscore:
            [
                'http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.4/underscore-min',
                'lib/underscore/underscore'
            ],
        modernizr:
            [
                'http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min',
                'lib/modernizr/modernizr-2.6.2-respond-1.1.0.min'
            ],
        text: 'lib/require/text',
        domReady: 'lib/require/domReady'
    },
    baseUrl: 'app/js',
    shim: {
        'twitter': {
            deps: ['jquery', 'underscore', 'text', 'domReady', 'modernizr','json3']
        },
        'angular': {
            deps: ['modernizr', 'twitter'],
            exports: 'angular'
        },
        'ngResource': {
            deps: ['angular']
        },
        'uiBootstrap': {
            deps: ['angular', 'ngResource']
        },
        'angularMocks': {
            deps: ['angular'],
            exports: 'angular.mock'
        },
        'app': {
            deps: ['angular', 'ngResource']
        },
        'services/PermissionsFactory': {
            deps: ['angular', 'ngResource', 'services']
        },
        'controllers': {
            deps: ['services']
        }
    },
    priority: [
        'angular'
    ]
});

require([
    'angular',
    'domReady',
    'app'
], function(angular, domReady, app) {
    'use strict';
    domReady(function() {
        angular.bootstrap(document, [app.name]);
    });
});
