package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.application.VersionException;
import br.unitins.clinica.model.PessoaFisica;
import br.unitins.clinica.model.TipoUsuario;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = -4714515783891349751L;
	
	public UsuarioController() {
		super(new UsuarioRepository());
	}

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		entity.setPessoaFisica(new PessoaFisica());
	
		return entity;
	}
	
	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}

	@Override
	public void incluir() {
		getEntity().setSenha(Util.hash(getEntity()));
		try {
			getRepository().save(getEntity());
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

}
