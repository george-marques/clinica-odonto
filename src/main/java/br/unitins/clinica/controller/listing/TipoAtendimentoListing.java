package br.unitins.clinica.controller.listing;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.TipoAtendimento;
import br.unitins.clinica.repository.TipoAtendimentoRepository;

@Named
@ViewScoped
public class TipoAtendimentoListing extends Listing<TipoAtendimento> implements Serializable {

	private static final long serialVersionUID = -6884165392267974185L;
	private String filtro;

	public TipoAtendimentoListing() {
		super("tipoatendimentolisting", new TipoAtendimentoRepository());
		
	}

	@Override
	public void pesquisar() {
		TipoAtendimentoRepository repo = new TipoAtendimentoRepository();
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
