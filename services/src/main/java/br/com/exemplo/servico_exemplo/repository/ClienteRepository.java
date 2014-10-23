package br.com.exemplo.servico_exemplo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.exemplo.servico_exemplo.domain.Cliente;

@Repository
public class ClienteRepository {

	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public List<Cliente> getTodosClientes() {
		return this.clientes;
	}
	
	public Long cadastrar(Cliente novo) {
		novo.setId(gerarIdCliente());
		clientes.add(novo);
		return novo.getId();
	}

	private Long gerarIdCliente() {
		Long maxId = 0L;
		for (Cliente cliente: getTodosClientes()) {
			if (cliente.getId().compareTo(maxId) > 0) {
				maxId = cliente.getId();
			}
		}
		return ++maxId;
	}

	public Cliente recuperarClientePorId(long id) {
		for (Cliente cliente: getTodosClientes()) {
			if (cliente.getId().compareTo(id) == 0) {
				return cliente;
			}
		}
		return null;
	}

	public boolean alterar(long id, Cliente clienteNovo) {
		Cliente clienteAntigo = recuperarClientePorId(id);
		if (clienteAntigo == null) {
			return false;
		} else {
			clienteAntigo.setNome(clienteNovo.getNome());
			clienteAntigo.setEmail(clienteNovo.getEmail());
			clienteAntigo.setRenda(clienteNovo.getRenda());
			return true;
		}
	}

	public boolean excluir(long id) {
		Cliente cliente = recuperarClientePorId(id);
//		getTodosClientes().indexOf(cliente);
		return getTodosClientes().remove(cliente);
	}

}
