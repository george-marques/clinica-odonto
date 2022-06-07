package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.Paciente;
import br.unitins.clinica.model.Venda;
import br.unitins.clinica.repository.VendaRepository;

@Named
@ViewScoped
public class HistoricoController extends Controller<Venda> implements Serializable {

	private static final long serialVersionUID = 8234297236398422824L;

	private List<Venda> listaVenda;

	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			VendaRepository repo = new VendaRepository();
			listaVenda = repo.listarTodos(entity);
		}
		return listaVenda;
	}


	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

	@Override
	public Venda getEntity() {
		if (entity == null)
			entity = new Venda();
		return entity;
	}

}
