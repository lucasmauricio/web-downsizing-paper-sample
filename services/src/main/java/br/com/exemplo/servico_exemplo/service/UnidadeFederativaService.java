package br.com.exemplo.servico_exemplo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.exemplo.servico_exemplo.domain.Cidade;

@Service
public class UnidadeFederativaService {

	public List<Cidade> getCidadesMock() {
		List<Cidade> cidadesMock = new ArrayList<Cidade>();
		cidadesMock.add(new Cidade(1L, "GO", "Goiânia", 1393579L, 739.492));
		cidadesMock.add(new Cidade(2L, "DF", "Brasília", 214529L, 472.12));
		cidadesMock.add(new Cidade(3L, "DF", "Águas Claras", 135685L, 31.50));
		cidadesMock.add(new Cidade(4L, "DF", "Planaltina", 164939L, 1534.69));
		cidadesMock.add(new Cidade(5L, "GO", "Ceres", 20924L, 213.497));
		cidadesMock.add(new Cidade(6L, "GO", "Anápolis", 357402L, 933.156));
		cidadesMock.add(new Cidade(7L, "GO", "Cidade de Goiás", 24727L, 3108.018));
	 	cidadesMock.add(new Cidade(8L, "GO", "Itapuranga", 26695L, 1277.160));
		cidadesMock.add(new Cidade(9L, "GO", "Campinaçú", 3656L, 1974.367));
		cidadesMock.add(new Cidade(10L, "GO", "Santa Terezinha de Goiás", 10304L, 1202.195));
		cidadesMock.add(new Cidade(11L, "GO", "Minaçú", 31149L, 2860.719));
		cidadesMock.add(new Cidade(12L, "AL", "Maceió", 23192L, 468.069));
		cidadesMock.add(new Cidade(13L, "PB", "João Pessoa", 769684L, 210.551));
		cidadesMock.add(new Cidade(14L, "PB", "Itaporanga", 23192L, 468.069));
		return cidadesMock;
	}

	public List<Cidade> getCidades(String siglaUf) {
		List<Cidade> cidades = new ArrayList<Cidade>();
		for (Cidade cidade: getCidadesMock()) {
			if (cidade.getUf().equalsIgnoreCase(siglaUf)) {
				cidades.add(cidade);
			}
		}
		return cidades;
	}
	
	public boolean validarUf(String siglaUf) {
		List<String> ufs = new ArrayList<String>();
		ufs.add("AL");
		ufs.add("DF");
		ufs.add("GO");
		ufs.add("PB");
		ufs.add("PE");
		return ufs.contains(siglaUf);
	}

}
