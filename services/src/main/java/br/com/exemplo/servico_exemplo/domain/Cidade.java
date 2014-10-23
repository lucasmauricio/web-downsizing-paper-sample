package br.com.exemplo.servico_exemplo.domain;

public class Cidade {

	private long id;
	private String uf;
	private String nome;
	private long populacao;
	private double area;
	
	public long getId() {
		return id;
	}	
	public void setId(long id) {
		this.id = id;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getPopulacao() {
		return populacao;
	}
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	
	public Cidade(long id, String uf, String nome, long populacao, double d) {
		super();
		this.id = id;
		this.uf = uf;
		this.nome = nome;
		this.populacao = populacao;
		this.area = d;
	}
}
