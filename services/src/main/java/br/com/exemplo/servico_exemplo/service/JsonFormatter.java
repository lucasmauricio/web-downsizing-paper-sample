package br.com.exemplo.servico_exemplo.service;

import java.util.List;

import br.com.exemplo.servico_exemplo.domain.Cidade;

public class JsonFormatter {
	
	private List<Cidade> cidades;

	public JsonFormatter(List<Cidade> cidadesUf) {
		this.cidades = cidadesUf;
	}
//TENTARJACKSON
//	JsonFactory factory = new JsonFactory();
//	StringWriter w = new StringWriter();
//	JsonGenerator gen = factory.createGenerator(w);
//	
//	gen.wri
//	
//	gen.writeObject(cidadesUf);
//	gen.close();
//	response.getWriter().print(gen);

	
//	response.getWriter().print(" [{\"id\":7788,\"descricao\":\"G 1115\",\"marca\":\"Aveli G\",\"modelo\":\"G1115\",\"fabricante_id\":\"8800\","
//			+ "\"categoria_id\":\"9900\",\"observacao\":\"\"},{\"id\":7799,\"descricao\":\"Lenovo i7 ultra\",\"marca\":\"Lenovo i7\",\"modelo\":"
//			+ "\"algum i7\",\"fabricante_id\":\"8888\",\"categoria_id\":\"9900\",\"observacao\":\"\"},{\"id\":7791,\"descricao\":\"hd rápico\","
//			+ "\"marca\":\"barracuda\",\"modelo\":\"xx897987\",\"fabricante_id\":\"8877\",\"categoria_id\":\"9999\",\"observacao\":\"esse dava "
//			+ "erro.\"},{\"id\":7797,\"descricao\":\"avel Titanium\",\"marca\":\"Titanium\",\"modelo\":\"B 154 SE2 Plus\",\"fabricante_id\":"
//			+ "\"8800\",\"categoria_id\":\"9900\",\"observacao\":\"\"},{\"id\":7796,\"descricao\":\"Avel B153\",\"marca\":\"Titanium\",\"modelo"
//			+ "\":\"B153\",\"fabricante_id\":\"8800\",\"categoria_id\":\"9900\",\"observacao\":\"\"}] ");

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		sb.append("[");
		for (Cidade cidade: cidades) {
			if (!first) {
				sb.append(",");
			}
			sb.append("{");
			sb.append(String.format("\"%s\": %s", "id", cidade.getId()));
			sb.append(String.format(", \"%s\": \"%s\"", "nome", cidade.getNome()));
			sb.append(String.format(", \"%s\": \"%s\"", "UF", cidade.getUf()));
			sb.append(String.format(", \"%s\": \"%s\"", "populacao", cidade.getPopulacao()));
			sb.append(String.format(", \"%s\": \"%s\"", "area", cidade.getArea()));
			sb.append("}");
			first = false;
		}
		sb.append("]");
		return sb.toString();
	}

}
