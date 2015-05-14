'use strict'

var droneshopControllers = angular.module('droneshopControllers', []);

droneshopControllers
.controller('LoginCtrl', function ($scope, $rootScope, AUTH_EVENTS, AuthService) {
    $scope.credentials = { username: '', password: ''};

    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (user) {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $scope.setCurrentUser(user);
            $scope.error = false;
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
            $scope.error = true;
        });
    };

    $scope.logout = function () {
        AuthService.logout();
        $rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
        $scope.setCurrentUser(null);
    };
})
.controller('ApplicationController', function ($scope,
                                               USER_ROLES,
                                               Category,
                                               AuthService) {
  $scope.currentUser = null;
  $scope.userRoles = USER_ROLES;
  $scope.isAuthorized = AuthService.isAuthorized;
 
  $scope.setCurrentUser = function (user) {
    $scope.currentUser = user;
  };
})

.controller('CatalogCtrl', ['$scope', '$routeParams', 'OffersInCategory',
    function($scope, $routeParams, OffersInCategory) {
        $scope.offers = OffersInCategory.query({categoryid: $routeParams.categoryid});
    }])

.controller('CategoryCtrl', 
    function($scope, Category) {
        $scope.categories = Category.query();
    })

.controller('OfferCtrl', ['$scope', '$routeParams', 'Offer',
    function($scope, $routeParams, Offer) {
        $scope.offer = Offer.get({offerid: $routeParams.offerid});
    }])

.controller('RegisterCtrl', ['$scope', 'RegisterService',
    function($scope, RegisterService) {
        $scope.userData = {username: '', password: '', firstName: '', lastName: '', phoneNumber: '', address: '', email: ''};

        $scope.success = false;
        $scope.error = false;

        $scope.register = function(userData) {
            RegisterService.register(userData)
                .then(function () {$scope.success = true; },
                      function () {$scope.error = true; })
        }
    }]);
