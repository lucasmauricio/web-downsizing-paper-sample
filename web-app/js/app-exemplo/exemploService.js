 

        var url = 'http://localhost:8080/';

        this.getDadosUsuario = function(_uf) {
        	return $http.get('api/usuario/' + _uf + '/cidades');
    	};

