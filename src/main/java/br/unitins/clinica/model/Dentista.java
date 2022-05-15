package br.unitins.clinica.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Dentista extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -3605786376959566572L;
	private String nome;
	private String email;
	private String telefone;
	private Double salario;

	public Dentista() {

	}

	public Dentista(String nome, String email, String telefone, Double salario) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.salario = salario;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
