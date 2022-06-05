package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.model.Clinica;
import br.unitins.clinica.model.PessoaJuridica;
import br.unitins.clinica.repository.ClinicaRepository;

@Named
@ViewScoped
public class ClinicaController extends Controller<Clinica> implements Serializable{

	private static final long serialVersionUID = -968234543986300665L;
	private List<Clinica> listaClinica;

	public ClinicaController() {
		super(new ClinicaRepository());
	}
	
	@Override
	public Clinica getEntity() {
		if(entity == null) {
			entity = new Clinica();
			entity.setPessoaJuridica(new PessoaJuridica());
		}
		return entity;
	}
	
	public void editar(Clinica clinica) {
		setEntity(clinica);
	}

	public void excluir(Clinica clinica) {
		entity = clinica;

		excluir();

	}
	
	@Override
	public void limpar() {
		setListaClinica(null);
		super.limpar();
	}
	
	public List<Clinica> getListaClinica() {

		if (listaClinica == null) {
			ClinicaRepository pr = new ClinicaRepository();
			listaClinica = pr.listarTodos(entity);
				
		}

		return listaClinica;
	}

	public void setListaClinica(List<Clinica> listaClinica) {
		this.listaClinica = listaClinica;
	}

}