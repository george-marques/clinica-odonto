package br.unitins.clinica.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class TipoAtendimento extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -6319545643688882087L;

	private String nome;

	private String descricao;
	private Double valor;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Consulta_TipoAtendimento", joinColumns = {
			@JoinColumn(name = "id_consulta") }, inverseJoinColumns = { @JoinColumn(name = "id_tipoAtendimento") })
	private List<Consulta> consultas;

	public TipoAtendimento() {

	}

	public TipoAtendimento(String nome, String descricao, Double valor) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

}
