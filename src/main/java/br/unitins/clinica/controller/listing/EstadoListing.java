package br.unitins.clinica.controller.listing;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.repository.EstadoRepository;
import br.unitins.clinica.model.Estado;

@Named
@ViewScoped
public class EstadoListing extends Listing<Estado> implements Serializable {

	private static final long serialVersionUID = -6529779004313576587L;

	private String filtro;

	public EstadoListing() {
		super("estadolisting", new EstadoRepository());
	}

	@Override
	public void pesquisar() {
		EstadoRepository repo = new EstadoRepository();
		try {
			setList(repo.findByNome(filtro));
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
