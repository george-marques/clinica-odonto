package br.unitins.clinica.controller;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Session;
import br.unitins.clinica.controller.listing.DentistaListing;

import br.unitins.clinica.controller.listing.PacienteListing;
import br.unitins.clinica.controller.listing.TipoAtendimentoListing;
import br.unitins.clinica.controller.listing.VendaListing;
import br.unitins.clinica.model.Consulta;
import br.unitins.clinica.model.Dentista;
import br.unitins.clinica.model.Paciente;
import br.unitins.clinica.model.PessoaFisica;
import br.unitins.clinica.model.TipoAtendimento;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.model.Venda;
import br.unitins.clinica.repository.ConsultaRepository;
import br.unitins.clinica.repository.TipoAtendimentoRepository;

@Named
@ViewScoped
public class ConsultaController extends Controller<Consulta> implements Serializable {

	private static final long serialVersionUID = -127684213819441346L;
	private Usuario usuarioLogado;

	public void editar(Consulta consulta) {
		setEntity(consulta);
	}

	public ConsultaController() {
		super(new ConsultaRepository());
		usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");

	}

	public void abrirDentistaListing() {
		DentistaListing listing = new DentistaListing();
		listing.open();
	}

	public void obterDentistaListing(SelectEvent<Dentista> event) {
		getEntity().setDentista(event.getObject());
	}

	public void abrirPacienteListing() {
		PacienteListing listing = new PacienteListing();
		listing.open();
	}

	public void obterPacienteListing(SelectEvent<Paciente> event) {
		getEntity().setPaciente(event.getObject());

	}

	public void abrirVendaListing() {
		VendaListing listing = new VendaListing();
		listing.open();
	}

	public void obterVendaListing(SelectEvent<Venda> event) {
		getEntity().setVenda(event.getObject());

	}

	public void abrirTipoAtendimentoListing() {
		TipoAtendimentoListing listing = new TipoAtendimentoListing();
		listing.open();
	}

	public void obterTipoAtendimentoListing(SelectEvent<TipoAtendimento> event) {
		getEntity().getListaTipoAtendimento().add(event.getObject());
	}

	public void removerTipo(TipoAtendimento categoria) {
		getEntity().getListaTipoAtendimento().remove(categoria);
	}

	public List<TipoAtendimento> completeTipoAtendimento(String filtro) {
		TipoAtendimentoRepository repo = new TipoAtendimentoRepository();
		try {
			return repo.findByNome(filtro);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<TipoAtendimento>();
		}
	}

	public Double valorConsulta() {
		Double valordConsulta = 0.0;

		if (getEntity().getVenda().getTotalVenda() == null) {
			getEntity().getVenda().setTotalVenda(0.0);
		}
		valordConsulta = getEntity().getVenda().getTotalVenda() + valorServico();

		return valordConsulta;
	}

	public Double valorServico() {
		Double valordServico = 0.0;
		Double total = 0.0;
		for (int i = 0; i < entity.getListaTipoAtendimento().size(); i++) {

			valordServico = getEntity().getListaTipoAtendimento().get(i).getValor();
			entity.setValorPagar(valordServico);

			total = total + valordServico;
		}
		return total;
	}

	@Override
	public void incluir() {
		entity.setValorPagar(valorConsulta());
		if (entity.getVenda().getId() == null) {
			entity.setVenda(null);
		}
		super.incluir();

	}

	public void incluir2() {
		super.incluir();
	}

	public void alterar(Consulta consulta) {
		this.entity = consulta;

		incluir2();
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@Override
	public Consulta getEntity() {
		if (entity == null) {
			entity = new Consulta();
			entity.setDentista(new Dentista());
			entity.setPaciente(new Paciente());
			entity.getPaciente().setPessoaFisica(new PessoaFisica());
			entity.setListaTipoAtendimento(new ArrayList<TipoAtendimento>());
			entity.setVenda(new Venda());
			entity.setUsuario(usuarioLogado);

		}

		return entity;
	}

}
