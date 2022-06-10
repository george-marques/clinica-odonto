package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.controller.listing.ConsultaListing;
import br.unitins.clinica.model.Consulta;
import br.unitins.clinica.model.RegistroAtendimento;
import br.unitins.clinica.model.Status;
import br.unitins.clinica.repository.RegistroAtendimentoRepository;

@Named
@ViewScoped
public class RegistroAtendimentoController extends Controller<RegistroAtendimento> implements Serializable {

	private static final long serialVersionUID = 619584683554055068L;

	public RegistroAtendimentoController() {
		super(new RegistroAtendimentoRepository());
	}

	public Status[] getListaStatus() {
		return Status.values();
	}

	@Override
	public RegistroAtendimento getEntity() {
		if (entity == null) {
			entity = new RegistroAtendimento();
			entity.setConsulta(new Consulta());

		}
		return entity;
	}
	
	@Override
	public void incluir() {
		if(entity.getConsulta().getStatus().getId() == 2) {
			entity.getConsulta().setStatus(Status.valueOf(1));
		}
		super.incluir();
	}

	public void abrirConsultaListing() {
		ConsultaListing listing = new ConsultaListing();
		listing.open();
	}

	public void obterConsultaListing(SelectEvent<Consulta> event) {
		getEntity().setConsulta(event.getObject());
	}


}
