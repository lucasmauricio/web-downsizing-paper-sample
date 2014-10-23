'use strict';

var app = angular.module('exemplo', []).controller('clienteController', function($scope, $http){

	$scope.mensagem_erro = '';
     $scope.resultado = '';
     $scope.novoCliente = {};

  $scope.carregarClientes = function() {
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
               console.log("error: "+exception);
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

  $scope.carregarClientes();

//     $scope.carregarDadosCliente(7);
});

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