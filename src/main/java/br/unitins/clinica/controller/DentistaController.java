package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.controller.listing.DentistaListing;
import br.unitins.clinica.model.Dentista;
import br.unitins.clinica.repository.DentistaRepository;

@Named
@ViewScoped
public class DentistaController extends Controller<Dentista> implements Serializable {

	private static final long serialVersionUID = 5175030602526769220L;

	public DentistaController() {
		super(new DentistaRepository());
	}

	@Override
	public Dentista getEntity() {
		if (entity == null)
			entity = new Dentista();
		return entity;
	}
	
	
	public void abrirDentistaListing() {
		DentistaListing listing = new DentistaListing();
		listing.open();
	}
	
	public void obterDentistaListing(SelectEvent<Dentista> event) {
		setEntity(event.getObject());
	}
	

}
