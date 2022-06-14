package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Session;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.ItemVenda;
import br.unitins.clinica.model.Produto;
import br.unitins.clinica.repository.ProdutoRepository;

@Named
@ViewScoped
public class VendaController extends Controller<Produto> implements Serializable {

	private static final long serialVersionUID = -6997159798138968730L;
	private List<Produto> listaProduto;
	private String filtro;

	public VendaController() {
		super(new ProdutoRepository());
	}

	public void pesquisar() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			listaProduto = repo.findByNome(getFiltro());
		} catch (RepositoryException e) {
			e.printStackTrace();
			listaProduto = new ArrayList<Produto>();
		}
	}

	// metodo utilizado para adicionar o produto no carrinhoi
	public void comprar(Produto produto) {

		@SuppressWarnings("unchecked")
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");
		// caso nao exista o carrinho, criar um espaco de memoria
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();

		ItemVenda item = new ItemVenda();
		item.setProduto(produto);
		item.setValor(produto.getValor());
		item.setQuantidade(1);

		// se existe no carrinho, atualizar a quantidade
		if (carrinho.contains(item)) {
			int index = carrinho.indexOf(item);
			int quantidade = carrinho.get(index).getQuantidade() + 1;
			carrinho.get(index).setQuantidade(quantidade);
			double precoAux = carrinho.get(index).getValor() + produto.getValor();
			carrinho.get(index).setValor(precoAux);

		} else {
			// adicionando o novo item no carrinho
			carrinho.add(item);
		}

		// adicionando / atualizando o carrinho na sessao
		Session.getInstance().set("carrinho", carrinho);

		Util.addInfoMessage("Produto adicionado no carrinho.");

	}

	public List<Produto> getListaProduto() {
		if (listaProduto == null) 
			listaProduto = new ArrayList<Produto>();
		
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
		}
		return entity;
	}

}
