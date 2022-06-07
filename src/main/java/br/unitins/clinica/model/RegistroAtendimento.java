package br.unitins.clinica.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class RegistroAtendimento extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -2634094673826753856L;
	private LocalDate dataRegistro;
	private String descricao;
	@OneToOne
	private Consulta consulta;

	public LocalDate getDataRegistro() {
		this.dataRegistro = LocalDate.now();
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

}
