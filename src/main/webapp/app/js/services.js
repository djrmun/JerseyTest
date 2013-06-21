define(['angular','ngResource'], function (angular,ngResource) {
	'use strict';
	
	var servicesModule = angular.module('cuaApp.services', ['ngResource']);

	servicesModule.value('version', '0.1');

	return servicesModule;
});