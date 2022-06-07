package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.model.Venda;

@Named
@ViewScoped
public class DetalheVendaController extends Controller<Venda> implements Serializable {

	private static final long serialVersionUID = -4680875597598196855L;
	
	public DetalheVendaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("vendaFlash");
		entity = (Venda) flash.get("vendaFlash");
	}

	@Override
	public Venda getEntity() {
		if (entity == null)
			entity = new Venda();

		return entity;
	}
	
	

}
