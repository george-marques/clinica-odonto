package br.unitins.clinica.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Consulta extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 4285683150784963090L;
	private String titulo;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private String descricao;
	private boolean status;


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
