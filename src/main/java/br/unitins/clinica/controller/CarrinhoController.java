package br.unitins.clinica.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Session;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.application.VersionException;
import br.unitins.clinica.model.ItemVenda;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.model.Venda;
import br.unitins.clinica.repository.VendaRepository;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 6872264885075124741L;
	private List<ItemVenda> listaItemVenda = null;
	private Double valorTotal;

	public void finalizar() {
		// verificar se existe um usuario logado no sistema
		Usuario usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuarioLogado == null) {
			Util.addErrorMessage("Faça o Login para concluir a venda.");

			Util.redirect("login.xhtml");
		}

		// verificar se existe algum produto no carrinho (sessao)
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");
		if (carrinho == null || carrinho.isEmpty()) {
			Util.addErrorMessage("Não existem produtos no carrinho");
			return;
		}

		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		// venda.setUsuario(usuarioLogado);
		venda.setListaItemVenda(carrinho);
		venda.setTotalVenda(getValorTotal());

		// salvando no banco de dados

		VendaRepository repo = new VendaRepository();
		try {
			venda = repo.save(venda);
			carrinho.clear();
			Util.addInfoMessage("Venda realizada com sucesso.");
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problemas ao realizar a venda.");
			e.printStackTrace();
		} catch (VersionException e) {
			e.printStackTrace();
		}

	}

	public void remover(ItemVenda item) {

		@SuppressWarnings("unchecked")
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");

		carrinho.remove(item);

	}

	@SuppressWarnings("unchecked")
	public List<ItemVenda> getListaItemVenda() {
		listaItemVenda = (List<ItemVenda>) Session.getInstance().get("carrinho");

		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
	private Double valorTotalVenda(List<ItemVenda> lista) {
		Double aux = 0.0;
		if (lista != null) {
			for (ItemVenda itemVenda : lista) {
				aux = aux + itemVenda.getValor();
			}
			return aux;
		}
		return null;
	}

	public Double getValorTotal() {
		if (valorTotal == null)
			valorTotal = valorTotalVenda(listaItemVenda);

		if (valorTotal == null)
			valorTotal = 0.0;

		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
