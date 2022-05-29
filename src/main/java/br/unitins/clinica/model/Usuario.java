package br.unitins.clinica.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Usuario extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -1801040627866967810L;
	private String login;
	private String senha;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(unique = true)
	private PessoaFisica pessoaFisica;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

}
