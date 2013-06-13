'use strict';

/* Filters */

angular.module('phonecatFilters', []).filter('checkmark', function() {
  return function(input) {
    return input ? '\u2713' : '\u2718';
  };
}).filter('sortAlphabetically', function() {
	return function(input) {
		if(input){
			return input.sort();
		}else{
			return input;
		}
		
	};
});
