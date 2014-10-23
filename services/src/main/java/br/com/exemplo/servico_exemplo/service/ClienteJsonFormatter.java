package br.com.exemplo.servico_exemplo.service;

import br.com.exemplo.servico_exemplo.domain.Cliente;


public class ClienteJsonFormatter {
	
	private Cliente cliente;

	public ClienteJsonFormatter(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append(String.format("\"%s\": %s", "id", cliente.getId()));
			sb.append(String.format(", \"%s\": \"%s\"", "nome", cliente.getNome()));
			sb.append(String.format(", \"%s\": \"%s\"", "email", cliente.getEmail()));
			sb.append(String.format(", \"%s\": \"%s\"", "renda", cliente.getRenda()));
			sb.append("}");
		return sb.toString();
	}

}
