'use strict'

var droneshopApp = angular.module('droneshopApp');

droneshopApp.controller('OfferCRUDCtrl', function($scope, $filter, $http, $routeParams, $resource) {
   $scope.sellerid = $routeParams.sellerid;

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
    $http.post('offer/delete', $scope.offers[index]);
    $scope.offers.splice(index, 1);
  };

  $scope.categories = [];
  $scope.loadCategories = function() {
      return $scope.categories.length ? null : $http.get('category/')
          .success(function(data) {
              $scope.categories = data;
          });
      };

  $scope.showCategory = function(offer) {
      if(offer.category && $scope.categories.length) {
        var selected = $filter('filter')($scope.categories, {id: offer.category});
        return selected.length ? selected[0].name: 'Not set';
      } else {
        return offer.category.name || 'Not set';
      }
  };

  // add user
  $scope.addOffer= function() {
    $scope.inserted = {
        "id": null,
        "name": "",
        "dateCreated": new Date(),
        "expirationDate": new Date(),
        "validTo": new Date(),
        "validFrom": new Date(),
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
