package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.Consulta;
import br.unitins.clinica.model.RegistroAtendimento;
import br.unitins.clinica.repository.ConsultaRepository;
import br.unitins.clinica.repository.RegistroAtendimentoRepository;

@Named
@ViewScoped
public class RegistroAtendimentoController extends Controller<RegistroAtendimento> implements Serializable {

	private static final long serialVersionUID = 619584683554055068L;
	private List<Consulta> listaConsulta;

	public RegistroAtendimentoController() {
		super(new RegistroAtendimentoRepository());
	}

	public void select(int id) {

		try {
			ConsultaRepository repo = new ConsultaRepository();
			entity.setConsulta(repo.findById(id));
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void incluir() {
		getEntity().getConsulta().setAtendido(true);
		Util.addInfoMessage("Atendimento realizado!");
		super.incluir();

	}

	@Override
	public void limpar() {
		super.limpar();
		listaConsulta = null;
	}


	public List<Consulta> getListaConsulta() {
		if (listaConsulta == null) {
			ConsultaRepository repo = new ConsultaRepository();
			try {
				listaConsulta = repo.findByConsulta();
			} catch (RepositoryException e) {

				e.printStackTrace();
			}
		}
		return listaConsulta;
	}

	public void setListaConsulta(List<Consulta> listaConsulta) {
		this.listaConsulta = listaConsulta;
	}

	@Override
	public RegistroAtendimento getEntity() {
		if (entity == null) {
			entity = new RegistroAtendimento();

		}
		return entity;
	}

}
