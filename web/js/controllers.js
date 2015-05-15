'use strict'

var droneshopControllers = angular.module('droneshopControllers', []);

droneshopControllers
.controller('LoginCtrl', function ($scope, $rootScope, AUTH_EVENTS, AuthService) {
    $scope.credentials = { username: '', password: ''};

    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (user) {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $scope.setCurrentUser(user, AuthService.role());
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
 
  $scope.setCurrentUser = function (user, role) {
    $scope.currentUser = user;
    $scope.role = role;
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

.controller('OfferCtrl', ['$scope', '$routeParams', '$http', 'Offer',
    function($scope, $routeParams, $http, Offer) {
        $scope.offer = Offer.get({offerid: $routeParams.offerid});
        $scope.sendComment = function() {
            $http.post("offer/"+$routeParams.offerid+"/comment", $scope.commentInput);
            $scope.commentInput = "";
        };

        $scope.addToCart = function() {
            $http.post("offer/"+$routeParams.offerid+"/buy")
            .success(function() {
                $scope.accepted = true;
                $scope.rejected = false;
            })
            .error(function() {
                $scope.rejected = true;
                $scope.accepted = false;
            });
        };
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
    }])

.controller('CartCtrl', ['$scope', 'Cart',
    function($scope, Cart) {
        $scope.payments = Cart.query();
    }
]);
