package br.unitins.clinica.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Usuario extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -1801040627866967810L;
	private TipoUsuario tipoUsuario;
	private String senha;

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
