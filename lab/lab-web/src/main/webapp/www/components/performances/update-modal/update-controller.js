'use strict';

angular.module('aceConcerts.components.performances.update.controller', [
  'aceConcerts.core.concert.service'
])
  .controller('UpdatePerformanceCtrl',
  function (
    $scope,
    $uibModalInstance,
    concertService,
    items
  ) {

    var vm = this;

    vm.performance = items;

    vm.selectedVenue = {};
    vm.selectedType = {};
    vm.showAlert = false;

    vm.performance.open = moment(vm.performance.open);
    vm.performance.openDate = vm.performance.open.toDate();
    vm.performance.close = moment(vm.performance.close);
    vm.performance.closeDate = vm.performance.close.toDate();

    vm.selectedType.value = vm.performance.performer.type;

    console.log(vm.performance);

    var dateOptions = {
      dateDisabled: false,
      formatYear: 'yy',
      maxDate: new Date(2020, 5, 22),
      minDate: new Date(),
      startingDay: 1
    };

    vm.openDatePop = {
      opened: false,
      open: function() {
        vm.openDatePop.opened = true;
        vm.openDatePop.dateOptions.maxDate = vm.performance.closeDate || new Date(2020, 5, 22);
      },
      dateOptions: R.clone(dateOptions)
    };

    vm.closeDatePop = {
      opened: false,
      open: function() {
        vm.closeDatePop.opened = true;
        vm.closeDatePop.dateOptions.minDate = vm.performance.openDate || new Date();
      },
      dateOptions: R.clone(dateOptions)
    };

    vm.updateOpenMoment = function() {
      vm.performance.open = moment(vm.performance.openDate);
    }

    vm.updateCloseMoment = function() {
      vm.performance.close = moment(vm.performance.closeDate);
    }

    vm.loadVenues = function () {
      return concertService.getVenues()
        .then(function(venues) {
          vm.venues = venues;
          vm.getPerformanceTypes();
          return;
        });
    }

    vm.getPerformanceTypes = function() {
      return concertService.getPerformanceTypes()
        .then(function(types) {
          for (var i = 0; i < vm.venues.length; i++) {
            if (vm.venues[i].name == vm.performance.venueName)
              vm.selectedVenue = vm.venues[i];
          }
          vm.accomodations = R.filter(selectedVenueHasType, types);
          vm.performance.venueName = vm.selectedVenue.name;

          for (var i = 0; i < vm.accomodations.length; i++) {
            if (vm.accomodations[i].value == vm.performance.performer.type)
            {
              vm.selectedType = vm.accomodations[i];
              return;
            }
          }

          return;
        });
    };

    vm.setPerformanceType = function() {
      vm.performance.performer.type = vm.selectedType.value;
    };

    vm.request = function () {
      var p = vm.performance;
      if (p.venueName && p.performer.name && p.performer.type && p.open && p.close) {
        $uibModalInstance.close(vm.performance);
      } else {
        vm.showAlert = true;
      }
    };

    vm.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };

    var selectedVenueHasType = function(type) {
      return R.contains(type.value, vm.selectedVenue.accomodations);
    }

    vm.loadVenues();

    console.log(vm);
});
