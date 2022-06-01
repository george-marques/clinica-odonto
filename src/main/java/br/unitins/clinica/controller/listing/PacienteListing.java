package br.unitins.clinica.controller.listing;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.Paciente;
import br.unitins.clinica.repository.PacienteRepository;

@Named
@ViewScoped
public class PacienteListing extends Listing<Paciente> implements Serializable {

	private static final long serialVersionUID = -816548310599872559L;
	private String filtro;

	public PacienteListing() {
		super("pacientelisting", new PacienteRepository());
	}

	@Override
	public void pesquisar() {
		PacienteRepository pr = new PacienteRepository();

		try {
			setList(pr.findByNome(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao realizar consulta");
		}

	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
