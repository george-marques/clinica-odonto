package br.unitins.clinica.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.unitins.clinica.model.Consulta;
import br.unitins.clinica.repository.ConsultaRepository;

@Named
@ViewScoped
public class ConsultaController extends Controller<Consulta> implements Serializable {

	private static final long serialVersionUID = -127684213819441346L;
	private ScheduleModel eventModel;
	private List<Consulta> listaConsulta;
	private ConsultaRepository cr;
	
	public ConsultaController() {
		super(new ConsultaRepository());
	}

	@SuppressWarnings("rawtypes")
	@PostConstruct
	public void init() {
		cr = new ConsultaRepository();
		entity = new Consulta();
		eventModel = new DefaultScheduleModel();

		listaConsulta = cr.listarTodos(entity);

		for (Consulta c : listaConsulta) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();

			evt.setTitle(c.getTitulo());
			evt.setStartDate(c.getDataInicio());
			evt.setEndDate(c.getDataFim());
			evt.setDescription(c.getDescricao());
			evt.setAllDay(true);
			evt.setEditable(true);

			eventModel.addEvent(evt);

		}

	}

	@SuppressWarnings("rawtypes")
	public void selecionaEvento(SelectEvent selectEvent) {

		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

		for (Consulta ev: listaConsulta) {
			if (ev.getId() == event.getData()) {
				entity = ev;
				break;
			}
		}
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public List<Consulta> getListaConsulta() {
		return listaConsulta;
	}

	public void setListaConsulta(List<Consulta> listaConsulta) {
		this.listaConsulta = listaConsulta;
	}

	@Override
	public Consulta getEntity() {
		if (entity == null) {
			entity = new Consulta();
		}
		
		return entity;
	}

}
