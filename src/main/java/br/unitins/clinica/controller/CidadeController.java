package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.controller.listing.CidadeListingSQL;
import br.unitins.clinica.controller.listing.EstadoListing;
import br.unitins.clinica.model.Cidade;
import br.unitins.clinica.model.Estado;
import br.unitins.clinica.repository.CidadeRepository;
import br.unitins.clinica.repository.EstadoRepository;

@Named
@ViewScoped
public class CidadeController extends Controller<Cidade> implements Serializable {

	private static final long serialVersionUID = -8309588021671288569L;

	public CidadeController() {
		super(new CidadeRepository());
	}

	@Override
	public Cidade getEntity() {
		if (entity == null) {
			entity = new Cidade();
			entity.setEstado(new Estado());
		}
		return entity;
	}
	
	
	public void abrirCidadeListingSQL() {
		CidadeListingSQL listing = new CidadeListingSQL();
		listing.open();
	}
	
	public void obterCidadeListingSQL(SelectEvent<Cidade> event) {
		setEntity(event.getObject());
	}
	
	public void abrirEstadoListing() {
		EstadoListing listing = new EstadoListing();
		listing.open();
	}
	
	public void obterEstadoListing(SelectEvent<Estado> event) {
		getEntity().setEstado(event.getObject());
	}

	public List<Estado> completeEstado(String filtro) {
		EstadoRepository repo = new EstadoRepository();
		try {
			return repo.findByNome(filtro, 4);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Estado>();
		}
	}

}
