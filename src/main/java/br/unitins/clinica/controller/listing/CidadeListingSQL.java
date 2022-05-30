package br.unitins.clinica.controller.listing;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.Cidade;
import br.unitins.clinica.repository.CidadeRepository;

@Named
@ViewScoped
public class CidadeListingSQL extends ListingSQL<Cidade> implements Serializable {

	private static final long serialVersionUID = 3301270147105129515L;
	private String filtro;

	public CidadeListingSQL() {
		super("cidadelistingsql", new CidadeRepository());
	}

	@Override
	public void pesquisar() {
		CidadeRepository repo = new CidadeRepository();
		try {
			setList(repo.findByNomeSQL(filtro));
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
