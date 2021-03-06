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
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;

@Entity
public class Consulta extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 4285683150784963090L;

	private LocalDate dataLancamento;

	@FutureOrPresent(message = "Informe uma data apartir do dia de hoje!")
	private LocalDateTime dataAgendamento;

	@ManyToMany
	private List<TipoAtendimento> listaTipoAtendimento;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dentista")
	private Dentista dentista;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Venda venda;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private boolean atendido = false;

	private Double valorPagar;

	public Double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public LocalDate getDataLancamento() {
		this.dataLancamento = LocalDate.now();
		return dataLancamento;
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

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAtendido() {
		return atendido;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}

}
