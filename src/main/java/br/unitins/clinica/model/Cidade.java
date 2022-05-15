package br.unitins.clinica.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Cidade extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 6461454015944838277L;
	@NotBlank(message = "A cidade deve ser informada")
	private String nome;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
