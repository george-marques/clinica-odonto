package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.controller.listing.DentistaListing;
import br.unitins.clinica.model.Dentista;
import br.unitins.clinica.repository.DentistaRepository;
import br.unitins.clinica.application.Util;

@Named
@ViewScoped
public class DentistaController extends Controller<Dentista> implements Serializable {

	private static final long serialVersionUID = 5175030602526769220L;

	public DentistaController() {
		super(new DentistaRepository());
	}

	public boolean validarCampos() {
		boolean retorno = true;

		if (getEntity().getNome() == null || getEntity().getNome().trim().equals("")) {
			Util.addErrorMessage("O nome deve ser informado.");
			retorno = false;
		}
		if (getEntity().getEmail() == null || getEntity().getEmail().trim().equals("")) {
			Util.addErrorMessage("O email deve ser informado.");
			retorno = false;
		}
		if (getEntity().getTelefone() == null) {
			Util.addErrorMessage("O telefone deve ser informado.");
			retorno = false;
		}
		if (getEntity().getSalario() == null) {
			Util.addErrorMessage("O salário deve ser informado.");
			retorno = false;
		}
		return retorno;
	}

	@Override
	public void incluir() {
		if (validarCampos()) {
			super.incluir();
		}
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
