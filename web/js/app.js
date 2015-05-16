'use strict' 
var droneshopApp = angular.module('droneshopApp', [
        'ngRoute',
        'droneshopControllers',
        'droneshopServices',
        'xeditable',
        'ui.bootstrap'
    ]);

droneshopApp.config(['$routeProvider', 
    function($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'partials/catalog.html',
                controller: 'CategoryCtrl'
            }).
            when('/category/:categoryid', {
                templateUrl: 'partials/catalog.html',
                controller: 'CategoryCtrl'
            }).
            when('/offer/:offerid', {
                templateUrl: 'partials/offer.html',
                controller: 'OfferCtrl'
            }).
            when('/cart', {
                templateUrl: 'partials/cart.html',
                controller: 'CartCtrl'
            }).
            when('/login', {
                templateUrl: 'partials/login.html'
            }).
            when('/register', {
                templateUrl: 'partials/register.html',
                controller: 'RegisterCtrl'
            }).
            when('/seller/:sellerid', {
                templateUrl: 'partials/selleroffers.html'
            }).
            when('/admin/:adminid', {
                templateUrl: 'partials/administration.html'
            }).
            otherwise('/');
    }]);

droneshopApp.config(function($resourceProvider) {
    $resourceProvider.defaults.stripTrailingSlashes = false;
});

droneshopApp.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});

droneshopApp.constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
});

droneshopApp.constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    editor: 'editor',
    guest: 'guest'
});

