'use strict';

angular.module('aceConcerts.sections.login.controller', [
  'aceConcerts.core.user.service'
])
  .controller(
    'LoginCtrl',
    function LoginCtrl(userService, $state) {

      // Dummy default data for login
      var vm = this;
      vm.email = 'shadowMan@redhat.com';
      vm.password = 123456;

      vm.showAlert = false;

      vm.submit = function() {
        if (vm.email == 'shadowMan@redhat.com' && vm.password == 123456) {
          userService.login(vm.email, vm.password)
          .then(function() {
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
