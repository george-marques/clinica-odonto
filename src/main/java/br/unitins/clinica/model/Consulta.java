package br.unitins.clinica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Consulta extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 4285683150784963090L;

	private LocalDate dataLancamento;

	private LocalDateTime dataAgendamento;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<TipoAtendimento> listaTipoAtendimento;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dentista")
	private Dentista dentista;

	public LocalDate getDataLancamento() {
		return LocalDate.now();
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public List<TipoAtendimento> getListaTipoAtendimento() {
		return listaTipoAtendimento;
	}

	public void setListaTipoAtendimento(List<TipoAtendimento> listaTipoAtendimento) {
		this.listaTipoAtendimento = listaTipoAtendimento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

}
