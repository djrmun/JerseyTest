/**
 * Created with JetBrains WebStorm.
 * User: va2839
 * Date: 6/28/13
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
/*global define*/
/*global console*/
/*global require*/

define(['directives'], function(directives) {
    'use strict';
    directives.directive('progressbar', function() {
        return {
            restrict: 'E,A',
            replace: true,
            template:
                '<div modal="showProgressBar" options="{backdropClick: false, backdropFade: true, dialogFade:true}">' +
                    '<div class="modal-header">' +
                        '<h4>Processing!</h4>' +
                    '</div>' +
                    '<div class="modal-body">' +
                        '<div class="progress progress-striped active">' +
                            '<div class="bar" style="width: 100%;"></div>' +
                        '</div>' +
                    '</div>' +
                '</div>',
            link: function(scope, element, attrs) {
                scope.showProgressBar = false;
            }
        };
    });
});


