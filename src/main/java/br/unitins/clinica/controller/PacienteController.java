package br.unitins.clinica.controller;

import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import br.unitins.clinica.controller.listing.EstadoListing;
import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Cidade;
import br.unitins.clinica.model.Endereco;
import br.unitins.clinica.model.Estado;
import br.unitins.clinica.model.Paciente;
import br.unitins.clinica.model.PessoaFisica;
import br.unitins.clinica.repository.PacienteRepository;
import br.unitins.clinica.model.Sexo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class PacienteController extends Controller<Paciente> implements Serializable {

	private static final long serialVersionUID = -7483885063497644996L;
	private List<Paciente> listaPaciente;
	private String nomePesquisa;
	private boolean skip;

	public PacienteController() {
		super(new PacienteRepository());
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	@Override
	public Paciente getEntity() {
		if (entity == null) {
			entity = new Paciente();
			entity.setPessoaFisica(new PessoaFisica());
			entity.getPessoaFisica().setEndereco(new Endereco());
			entity.getPessoaFisica().getEndereco().setCidade(new Cidade());
			entity.getPessoaFisica().getEndereco().getCidade().setEstado(new Estado());
		}
		return entity;
	}
	
	@Override
	protected void limparRelacionamentosNaoObrigatorios() {
		if (getEntity().getPessoaFisica().getEndereco().getCidade().getEstado().getId() == null)
			getEntity().getPessoaFisica().getEndereco().getCidade().setEstado(null);
	}
	
	public void abrirEstadoListing() {
		EstadoListing listing = new EstadoListing();
		listing.open();
	}
	
	public void obterEstadoListing(SelectEvent<Estado> event) {
		getEntity().getPessoaFisica().getEndereco().getCidade().setEstado(event.getObject());
	}

	public void editar(Paciente paciente) {
		setEntity(paciente);
	}

	public void excluir(Paciente paciente) {
		entity = paciente;

		excluir();

	}

	public void pesquisar() {
		PacienteRepository repo = new PacienteRepository();
		try {
			listaPaciente = repo.findByNome(getNomePesquisa());
		} catch (RepositoryException e) {
			e.printStackTrace();
			listaPaciente = new ArrayList<Paciente>();
		}
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	@Override
	public void limpar() {
		setListaPaciente(null);
		super.limpar();
	}

	public List<Paciente> getListaPaciente() {

		if (listaPaciente == null) {
			PacienteRepository pr = new PacienteRepository();
			listaPaciente = pr.listarTodos(entity);
		}

		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
