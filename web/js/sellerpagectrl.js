'use strict'

var droneshopApp = angular.module('droneshopApp');

droneshopApp.controller('OfferCRUDCtrl', function($scope, $filter, $http, $routeParams, $resource) {
  $http.get('offer/', {seller: $routeParams.sellerid}).
    success(function(res) {
      $scope.offers = res;
    });
   $scope.saveOffer= function(data, id) {
    //$scope.user not updated yet
    angular.extend(data, {id: id});
    return $http.post('offer/create', data);
  };

  // remove user
  $scope.removeOffer= function(index) {
    $scope.offer.splice(index, 1);
  };

  // add user
  $scope.addOffer= function() {
    $scope.inserted = {
        "id": null,
        "name": "",
        "dateCreated": null,
        "expirationDate": null,
        "validTo": null,
        "validFrom": null,
        "salePrice": null,
        "maxOffers": null,
        "description": "",
        "purchasedOffers": null,
        "active": true,
        "regularPrice": null 
    }
    $scope.offers.push($scope.inserted);
  };
});
