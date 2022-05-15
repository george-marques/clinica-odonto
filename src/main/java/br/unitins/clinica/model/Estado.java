package br.unitins.clinica.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Estado extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -1780840693906380534L;
	private String nome;
	private String sigla;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
