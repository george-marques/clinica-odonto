package br.unitins.clinica.controller.listing;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.model.Venda;
import br.unitins.clinica.repository.VendaRepository;

@Named
@ViewScoped
public class VendaListing extends Listing<Venda> implements Serializable {

	private static final long serialVersionUID = -5296231645392090198L;
	private String filtro;

	public VendaListing() {
		super("vendalisting", new VendaRepository());

	}

	@Override
	public void pesquisar() {
		VendaRepository repo = new VendaRepository();
		Venda venda = new Venda();
		
		setList(repo.listarTodos(venda));
		

	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
