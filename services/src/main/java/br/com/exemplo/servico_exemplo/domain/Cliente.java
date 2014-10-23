package br.com.exemplo.servico_exemplo.domain;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1129674690238665612L;

	private Long id;
	private String nome;
	private String email;
	private String renda;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRenda() {
		return renda;
	}
	public void setRenda(String renda) {
		this.renda = renda;
	}
	
}
