package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Produto;
import br.unitins.clinica.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> implements Serializable {

	private static final long serialVersionUID = -8174698705705881754L;
	private List<Produto> listaProduto;
	private String nomePesquisa;

	public ProdutoController() {
		super(new ProdutoRepository());

	}

	public void editar(Produto produto) {
		setEntity(produto);
	}

	public void excluir(Produto produto) {
		entity = produto;

		excluir();

	}

	public void pesquisar() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			listaProduto = repo.findByNome(getNomePesquisa());
		} catch (RepositoryException e) {
			e.printStackTrace();
			listaProduto = new ArrayList<Produto>();
		}
	}

	@Override
	public void limpar() {
		setListaProduto(null);
		super.limpar();
	}

	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
		}
		return entity;
	}

	public List<Produto> getListaProduto() {

		if (listaProduto == null) {
			ProdutoRepository pr = new ProdutoRepository();
			listaProduto = pr.listarTodos(entity);
		}

		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

}
