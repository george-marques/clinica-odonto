package br.unitins.clinica.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Session;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.TipoUsuario;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.repository.UsuarioRepository;

@Named
@RequestScoped
public class LoginController extends Controller<Usuario> {

	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}

	public String entrar() throws RepositoryException {

		UsuarioRepository repo = new UsuarioRepository();
		Usuario usu = repo.verificarUsuario(entity.getTipoUsuario(), Util.hash(entity));

		if (usu != null) {
			Session.getInstance().set("usuarioLogado", usu);

			Util.redirect("template.xhtml");
		}
		Util.addErrorMessage("Usuario ou senha invalido.");

		return null;
	}

	@Override
	public Usuario getEntity() {
		if (entity == null) {
			entity = new Usuario();
		}
		return entity;
	}

}
