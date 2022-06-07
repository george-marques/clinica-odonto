package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.RegistroAtendimento;
import br.unitins.clinica.repository.RegistroAtendimentoRepository;

@Named
@ViewScoped
public class RelatorioAtendimentoController implements Serializable {

	private static final long serialVersionUID = -2927392858225070971L;

	private String filtro;
	private List<RegistroAtendimento> listaAtendimento;

	public void pesquisar() {
		RegistroAtendimentoRepository repo = new RegistroAtendimentoRepository();
		try {
			setListaAtendimento(repo.findByDentista(filtro));
		} catch (RepositoryException e) {
			setListaAtendimento(null);
		}
	}

	public void limpar() {
		filtro = null;
		setListaAtendimento(null);
	}

	public void gerarRelatorio() {
		Util.redirect("/Clinica/relatorioatendimentos.xhtml?DENTISTA=" + getFiltro().trim());
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<RegistroAtendimento> getListaAtendimento() {
		if (listaAtendimento == null)
			listaAtendimento = new ArrayList<RegistroAtendimento>();
		return listaAtendimento;
	}

	public void setListaAtendimento(List<RegistroAtendimento> listaAtendimento) {
		this.listaAtendimento = listaAtendimento;
	}
}
