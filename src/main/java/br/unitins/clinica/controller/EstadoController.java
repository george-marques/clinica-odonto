package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.controller.listing.EstadoListing;
import br.unitins.clinica.repository.EstadoRepository;
import br.unitins.clinica.model.Estado;

@Named
@ViewScoped
public class EstadoController extends Controller<Estado> implements Serializable{


	private static final long serialVersionUID = 6951981547672763616L;

	public EstadoController() {
		super(new EstadoRepository());
	}
	
	@Override
	public Estado getEntity() {
		if (entity == null)
			entity = new Estado();
		return entity;
	}
	
	public void abrirEstadoListing() {
		EstadoListing listing = new EstadoListing();
		listing.open();
	}
	
	public void obterEstadoListing(SelectEvent<Estado> event) {
		setEntity(event.getObject());
	}

}
