package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.controller.listing.ConsultaListing;
import br.unitins.clinica.model.Consulta;
import br.unitins.clinica.model.RegistroAtendimento;
import br.unitins.clinica.repository.RegistroAtendimentoRepository;

@Named
@ViewScoped
public class RegistroAtendimentoController extends Controller<RegistroAtendimento> implements Serializable {

	private static final long serialVersionUID = 619584683554055068L;

	public RegistroAtendimentoController() {
		super(new RegistroAtendimentoRepository());
	}

	@Override
	public RegistroAtendimento getEntity() {
		if (entity == null) {
			entity = new RegistroAtendimento();
			entity.setConsulta(new Consulta());

		}
		return entity;
	}

	public void abrirConsultaListing() {
		ConsultaListing listing = new ConsultaListing();
		listing.open();
	}

	public void obterConsultaListing(SelectEvent<Consulta> event) {
		getEntity().setConsulta(event.getObject());
	}

	@Override
	public void incluir() {
		entity.getConsulta().setAtendido(true);
		super.incluir();
	}

}
