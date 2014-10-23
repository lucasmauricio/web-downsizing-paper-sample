package br.com.exemplo.servico_exemplo.service;

import java.util.List;

import br.com.exemplo.servico_exemplo.domain.Cliente;

public class ListJsonFormatter {
	
	private List<Cliente> clientes;

	public ListJsonFormatter(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		sb.append("[");
		for (Cliente cliente: clientes) {
			if (!first) {
				sb.append(",");
			}
			sb.append(new ClienteJsonFormatter(cliente));
//			sb.append("{");
//			sb.append(String.format("\"%s\": %s", "id", cidade.getId()));
//			sb.append(String.format(", \"%s\": \"%s\"", "nome", cidade.getNome()));
//			sb.append(String.format(", \"%s\": \"%s\"", "UF", cidade.getUf()));
//			sb.append(String.format(", \"%s\": \"%s\"", "populacao", cidade.getPopulacao()));
//			sb.append(String.format(", \"%s\": \"%s\"", "area", cidade.getArea()));
//			sb.append("}");
			first = false;
		}
		sb.append("]");
		return sb.toString();
	}

}
