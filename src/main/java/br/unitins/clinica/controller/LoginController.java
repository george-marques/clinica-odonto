package br.unitins.clinica.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Session;
import br.unitins.clinica.application.Util;

import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.repository.UsuarioRepository;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;

	public void entrar() throws RepositoryException {

		UsuarioRepository repo = new UsuarioRepository();
		Usuario usuarioLogado = null;
		try {
			usuarioLogado = repo.validarLogin(usuario.getLogin(), 
					Util.hash(usuario));
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		if (usuarioLogado != null && usuarioLogado.isAtivo()) {
			Session.getInstance().set("usuarioLogado", usuarioLogado);
			Util.redirect("pages/consulta.xhtml");
		}
		Util.addErrorMessage("Login ou senha inválido.");

	}

	public void limpar() {
		usuario = null;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
