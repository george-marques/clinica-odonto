package br.unitins.clinica.controller.listing;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.repository.DentistaRepository;
import br.unitins.clinica.model.Dentista;

@Named
@ViewScoped
public class DentistaListing extends Listing<Dentista> implements Serializable{

	
	private static final long serialVersionUID = -7243377669850165719L;
	private String filtro;

	public DentistaListing() {
		super("dentistalisting", new DentistaRepository());
	}

	@Override
	public void pesquisar() {
		DentistaRepository repo = new DentistaRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao realizar a consulta.");
		}
	}
	
	public void pesquisarAtivos() {
		DentistaRepository repo = new DentistaRepository();
		try {
			setList(repo.findByAtivos(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao realizar a consulta.");
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
