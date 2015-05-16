'use strict'

var droneshopServices = angular.module('droneshopServices', ['ngResource']);

droneshopServices.factory('AuthService', function ($http, Session) {
  var authService = {};
 
  authService.login = function (credentials) {
    return $http
      .post('LoginController', credentials)
      .then(function (res) {
          Session.create(res.data.sessionid, res.data.user.id, res.data.role);
        return res.data.user;
      });
  };

  authService.role = function() {
      return Session.userRole;
  };

  authService.logout = function() {
    $http.post('LoginController', {"logout":true});
    Session.destroy();
  };
 
  authService.isAuthenticated = function () {
    return !!Session.userId;
  };
 
  authService.isAuthorized = function (authorizedRoles) {
    if (!angular.isArray(authorizedRoles)) {
      authorizedRoles = [authorizedRoles];
    }
    return (authService.isAuthenticated() &&
      authorizedRoles.indexOf(Session.userRole) !== -1);
  };
 
  return authService;
});

droneshopServices.factory('RegisterService', function($http) {
    var registerService = {};

    registerService.register = function (userData) {
        return $http.post('RegisterServlet', userData)
    };

    return registerService;
})
    

droneshopServices.service('Session', function () {
  this.create = function (sessionId, userId, userRole) {
    this.id = sessionId;
    this.userId = userId;
    this.userRole = userRole;
  };
  this.destroy = function () {
    this.id = null;
    this.userId = null;
    this.userRole = null;
  };
})

droneshopServices.factory('OffersInCategory', ['$resource',
    function($resource) {
        return $resource('offer/', {category: "@categoryid"});
    }]);

droneshopServices.factory('Category', ['$resource', '$routeParams',
  function($resource, $routeParams){
    return $resource('category/' + $routeParams.categoryid, {}, {
      query: {method:'GET', isArray:true}
    });
  }]);
 
droneshopServices.factory('Offer', ['$resource',
    function($resource) {
        return $resource('offer/:offerid', {}, {
            get: {method: 'GET'}
    })
}]);

droneshopServices.factory('Cart', ['$resource',
    function($resource) {
        return $resource('cart', {}, {
            query: {method: 'GET', isArray:true}
        })
    }]);
