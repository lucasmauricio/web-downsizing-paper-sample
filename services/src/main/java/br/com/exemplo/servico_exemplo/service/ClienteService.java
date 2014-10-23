package br.com.exemplo.servico_exemplo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo.servico_exemplo.domain.Cliente;
import br.com.exemplo.servico_exemplo.repository.ClienteRepository;
import br.com.exemplo.servico_exemplo.service.exception.ClienteCadastroInvalidoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Long cadastrar(Cliente novoCliente) throws ClienteCadastroInvalidoException {
		validarCadastro(novoCliente);
		return clienteRepository.cadastrar(novoCliente);
	}
	
	public List<Cliente> listarTodosClientes() {
		return clienteRepository.getTodosClientes();
	}

	private void validarCadastro(Cliente novoCliente) throws ClienteCadastroInvalidoException {
		List<String> erros = new ArrayList<String>();
		if (novoCliente == null) {
			throw new ClienteCadastroInvalidoException("Nenhum dado recebido para o cadastro do cliente.");
		}
		if (novoCliente.getNome() == null || novoCliente.getNome().isEmpty()) {
			erros.add("O nome é obrigatório para o cadastro de um cliente.");
		}
		if (novoCliente.getEmail() == null || novoCliente.getEmail().isEmpty()) {
			erros.add("O e-mail é obrigatório para o cadastro de um cliente.");
		}
		if (erros.size() > 0) {
			throw new ClienteCadastroInvalidoException(String.format("%d problema(s) com os dados a serem cadastrados: %s", erros.size(), erros.toString()));
		}
	}

	public Cliente buscarCliente(long id) {
		return clienteRepository.recuperarClientePorId(id);
	}

	public boolean alterarCliente(long id, Cliente cliente) throws ClienteCadastroInvalidoException {
		validarCadastro(cliente);
		return clienteRepository.alterar(id, cliente);
	}

	public boolean excluirCliente(long id) {
		return clienteRepository.excluir(id);
	}

}
