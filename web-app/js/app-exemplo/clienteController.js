'use strict';

var app = angular.module('artigoDonwsizingExemploApp', ['ngRoute']);


app.config(function($routeProvider, $locationProvider) {

  //$locationProvider.html5Mode(true);

  $routeProvider.
    when('/clientes', {
      templateUrl: 'cliente-GET.html',
      controller: 'clienteController'
    }).
    when('/novo-cliente', {
      templateUrl: 'cliente-POST.html',
      controller: 'clienteController'
    }).
    when('/cliente/:idCliente', {
      templateUrl: 'cliente-PUT.html',
      controller: 'clienteController'
    }).
    otherwise({
      templateUrl: 'inicio.html',
      controller: 'principalController'
//      redirectTo: '/index.html'
    });

  // use the HTML5 History API
  $locationProvider.html5Mode(true);

});


app.controller('principalController', function($scope) {
  $scope.message = "Everyone come and see how good it looks!"
})


app.controller('clienteController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

	$scope.mensagem_erro = '';
  $scope.resultado = '';
  $scope.novoCliente = {};

  $scope.carregarClientes = function() {
//    $console.log('AQUI');
        var url = 'http://localhost:8080/clientes/';
             $http.get(url)
               .success(function(data) {
                    $scope.clientes = data;
               })
                .error(function (exception) {
                    $scope.mensagem_erro = 'Ocorreu um erro ao tentar recuperar os clientes.' + exception;
               });
  };

  $scope.excluirCliente = function(_cliente) {
        var url = 'http://localhost:8080/cliente/';
             $http.delete(url + _cliente.id + '/excluir')
               .success(function(data, status) {
                   console.log(data);
                   console.log('status: ' + status);
                    $scope.carregarClientes();
               })
                .error(function (exception) {
                    $scope.mensagem_erro = 'Ocorreu um erro ao tentar excluir o cliente ' + _cliente.id + ': ' + exception;
               });
  };

  $scope.carregarDadosCliente = function(_id) {
      var url = 'http://localhost:8080/cliente/';
           $http.get(url + _id)
             .success(function(data) {
                  $scope.cliente = data;
             })
               .error(function (exception) {
                  $scope.mensagem_erro = 'Ocorreu um erro ao tentar recuperar os dados do cliente ' + _id + ': ' + exception;
             });
   };

  $scope.atualizarCadastroCliente = function(_cliente) {
      var url = 'http://localhost:8080/cliente/';
      $http.put(url + _cliente.id, _cliente)
        .success(function(data, status) {
             console.log(data);
             console.log('status: ' + status);
             $scope.resultado = data;
             if (status == 201) {
                  $scope.resultado = 'Cliente alterado com sucesso!';
             };
        })
        .error(function (exception, status) {
             console.log('status: ' + status);
             console.log("error: " + exception);
             $scope.resultado = 'Ocorreu um erro ao tentar alterar os dados do cliente ' + _cliente.id + ': ' + exception;
        });
  };

	$scope.cadastrarNovoCliente = function(_novoCliente) {
        var url = 'http://localhost:8080/clientes/cadastrar';
        $http.post(url, _novoCliente)
          .success(function(data, status) {
               console.log(data);
               console.log('status: ' + status);
	     	$scope.resultado = data;
               if (status == 201) {
                    $scope.resultado_sucesso = 'Cliente cadastrado com sucesso!';
                    $scope.resultado_erro = '';
               };
	     })
	     .error(function (exception) {
               console.log("error: "+exception);
	        	$scope.resultado_erro = '';
            $scope.resultado_erro = 'Ocorreu um erro ao tentar cadastrar um novo cliente: ' + exception;
	     });
	};

  // carga inicial de dados das views
  $scope.carregarClientes();
  if (typeof($routeParams.idCliente) != "undefined") {
    $scope.carregarDadosCliente($routeParams.idCliente);
  };

//     $scope.carregarDadosCliente(7);
}]);

app.directive('ngConfirmClick', [
        function(){
            return {
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.confirmedClick;
                    element.bind('click',function (event) {
                        if ( window.confirm(msg) ) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
    }])