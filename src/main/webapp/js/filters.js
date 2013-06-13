'use strict';

/* Filters */

Array.prototype.getUnique = function(){
	var u = {}, unique = [];
	for (var i = 0; i < this.length; i++) {
		var domainprefix = this[i].split('.')[0];
		if(domainprefix=="commontopology"){
			domainprefix="serviceorder";
		}
		if(domainprefix=="alert"){
			domainprefix="serviceorder";
		}
		if(domainprefix=="canned"){
			domainprefix="reports";
		}
		if(domainprefix=="mediation"){
			domainprefix="reports";
		}
		if(u.hasOwnProperty(domainprefix)){
			continue;
		}
		unique.push(domainprefix);
		u[domainprefix]=1;
	};

	return unique;
};

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
}).filter('extractDomainsForPermissions',function(){
	return function(input) {
		if(input){
			return input.getUnique();
		}else{
			return input;
		}
	};
});
