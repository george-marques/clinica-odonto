package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.application.Util;
import br.unitins.clinica.controller.listing.TipoAtendimentoListing;
import br.unitins.clinica.model.TipoAtendimento;
import br.unitins.clinica.repository.TipoAtendimentoRepository;

@Named
@ViewScoped
public class TipoAtendimentoController extends Controller<TipoAtendimento> implements Serializable {

	private static final long serialVersionUID = -7875813700967897577L;
	private List<TipoAtendimento> listaTipo;

	public TipoAtendimentoController() {
		super(new TipoAtendimentoRepository());
	}

	@Override
	public TipoAtendimento getEntity() {
		if (entity == null) {
			entity = new TipoAtendimento();
		}
		return entity;
	}

	public boolean validarCampos() {
		boolean retorno = true;

		if (getEntity().getNome() == null || getEntity().getNome().trim().equals("")) {
			Util.addErrorMessage("O nome deve ser informado.");
			retorno = false;
		}
		if (getEntity().getValor() == null) {
			Util.addErrorMessage("O valor deve ser informado.");
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
	public void limpar() {
		setListaTipo(null);
		super.limpar();
	}

	public void abrirTipoListing() {
		TipoAtendimentoListing listing = new TipoAtendimentoListing();
		listing.open();
	}

	public void obterTipoListing(SelectEvent<TipoAtendimento> event) {
		setEntity(event.getObject());
	}

	public List<TipoAtendimento> getListaTipo() {
		if (listaTipo == null) {
			listaTipo = getRepository().listarTodos(entity);
		}
		return listaTipo;
	}

	public void setListaTipo(List<TipoAtendimento> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public void selecionarItem(TipoAtendimento tipo) {
		this.entity = tipo;
	}

}
