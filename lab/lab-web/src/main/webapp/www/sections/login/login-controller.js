'use strict';

angular.module('aceConcerts.sections.login.controller', [
  'aceConcerts.core.user.service'
])
  .controller(
    'LoginCtrl',
    function LoginCtrl(userService, $state) {

      // Dummy default data for login
      var vm = this;

      vm.showAlert = false;

      vm.submit = function() {
        if (vm.email != null && vm.password != null) {
          userService.login(vm.email, vm.password)
          .then(function(res) {
              // console.log(res.join(""));
            return $state.go('home');
          });
        }
        else {
            vm.showAlert = true;
        }
      };

      console.log('Login Controller Created');
    }
  );
