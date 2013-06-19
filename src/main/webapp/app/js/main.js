require.config({
	paths: {
		jquery: ['http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min','lib/jquery/jquery'],
		angular: ['http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min','lib/angular/angular'],
		ngResource: ['http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular-resource.min','lib/angular/angular-resource'],
		twitter: ['http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min','lib/bootstrap/bootstrap'],
		underscore: ['http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.4/underscore-min','lib/underscore/underscore'],
		modernizr: ['http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min','lib/modernizr/modernizr-2.6.2-respond-1.1.0.min'],
		text: 'lib/require/text',
		domReady : 'lib/require/domReady'
	},
	baseUrl: 'app/js',
	shim: {
		'twitter' : {
			deps:['jquery','underscore','text','domReady','modernizr']
		},
		'angular' : { 
			deps:['modernizr','twitter'],
			exports : 'angular'
		},
		'ngResource' : {
			deps:['angular']
		},
		'angularMocks': {
			deps:['angular'],
			exports:'angular.mock'
		},
		'app': {
			deps:['angular','ngResource']
		},
		'services/PermissionsFactory' : {
			deps:['angular','ngResource','services']
		},
		'controllers': {
			deps:['services']
		}
	},
	priority: [
		"angular"
	]
});

require( [
	'angular',
	'domReady',
	'app'
], function(angular, domReady,app) {
	console.log('BootStrapping application');
	domReady(function() {
		console.log('Bootstrapping application');
		angular.bootstrap(document, [app['name']]);
	});
});
