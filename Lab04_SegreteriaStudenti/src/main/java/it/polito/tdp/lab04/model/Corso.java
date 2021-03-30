package it.polito.tdp.lab04.model;

public class Corso {

	private String codice;
	private int crediti;
	private String nome;
	private int periodo;
	
	public Corso(String codice, int crediti, String nome, int periodo) {
	
		this.codice = codice;
		this.crediti = crediti;
		this.nome = nome;
		this.periodo = periodo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	public String toString() {
		return this.nome;
	}
}
