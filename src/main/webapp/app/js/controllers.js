define(['angular', 'services'], function (angular) {
	'use strict';

	return angular.module('cuaApp.controllers', ['cuaApp.services'])
		.controller('MyCtrl1', ['$scope', 'version', function ($scope, version) {
			$scope.scopedAppVersion = version;
		}]);
});