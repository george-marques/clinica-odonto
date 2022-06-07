package br.unitins.clinica.controller.listing;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.Consulta;
import br.unitins.clinica.repository.ConsultaRepository;

@Named
@ViewScoped
public class ConsultaListing extends Listing<Consulta> implements Serializable {

	private static final long serialVersionUID = -2370735452890626538L;

	public ConsultaListing() {
		super("consultalisting", new ConsultaRepository());
	}

	@Override
	public void pesquisar() {
		ConsultaRepository repo = new ConsultaRepository();
		try {
			setList(repo.findByConsulta());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao realizar a consulta.");
		}
	}

}
