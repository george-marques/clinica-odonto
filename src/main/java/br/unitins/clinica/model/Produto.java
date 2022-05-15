package br.unitins.clinica.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Produto extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 4441411817620579912L;
	@NotBlank(message = "A descrição deve ser informada")
	private String descricao;
	
	private Double valor;
	
	private int estoque;

	public Produto() {

	}

	public Produto(String descricao, Double valor, int estoque) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.estoque = estoque;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

}
