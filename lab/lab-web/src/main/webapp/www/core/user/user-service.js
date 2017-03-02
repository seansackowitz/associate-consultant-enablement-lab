'use strict';

angular.module('aceConcerts.core.user.service', [])
  .factory(
    'userService',
    function userService($q, $resource) {


      var camel = 'http://localhost:8081';
      var loginEndpoint = $resource(camel + '/login');

      function authenticateLogin(login) {

      }

      var login = function(email, password) {
        return loginEndpoint.save({
          password: password,
          email: email
        }).$promise.then(function(res) {
          console.log(res);
          return res;
        });
      };

      return {
        login: login
      };
    }
  );
