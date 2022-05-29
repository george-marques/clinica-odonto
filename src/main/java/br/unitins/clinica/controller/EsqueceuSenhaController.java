package br.unitins.clinica.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.Email;
import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.application.VersionException;
import br.unitins.clinica.model.EsqueceuSenha;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.repository.EsqueceuSenhaRepository;
import br.unitins.clinica.repository.UsuarioRepository;

@Named
@ViewScoped
public class EsqueceuSenhaController extends Controller<EsqueceuSenha> implements Serializable {

	private static final long serialVersionUID = -8210073220145769614L;

	private String email;


	public void enviarEmail() {

		// 1 - Buscar o usuario
		UsuarioRepository repo = new UsuarioRepository();
		Usuario usuario = null;
		try {
			usuario = repo.findByEmail(getEmail());
			if (usuario == null) {
				Util.addErrorMessage("Email não encontrado em nosso banco de dados.");
				return;
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao encontrar o email.");
			return;
		}
		// 2 - Gerar codigo
		Random r = new Random();
		String codigo = new DecimalFormat("T-000000").format(r.nextInt(1000000));
		System.out.println(codigo);

		// 3 - EsqueceuSenha
		EsqueceuSenha esqueceu = new EsqueceuSenha();
		esqueceu.setUsuario(usuario);
		esqueceu.setCodigo(codigo);
		esqueceu.setDataHoraLimite(LocalDateTime.now().plusDays(1));
		esqueceu.setUtilizado(false);
		EsqueceuSenhaRepository repoEsqueceu = new EsqueceuSenhaRepository();
		try {
			repoEsqueceu.save(esqueceu);
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao gerar o código, tente novamente.");
			e.printStackTrace();
		} catch (VersionException e) {
			Util.addErrorMessage("Problema ao gerar o código, tente novamente.");
			e.printStackTrace();
		}

		// 4 - enviar email
		Email email = new Email(usuario.getPessoaFisica().getEmail(), "Esqueceu a senha",
				"Segue o código de recuperar a senha: " + codigo);
		if (!email.enviar()) {
			Util.addErrorMessage("Problema ao enviar o email.");
		} else
			Util.addInfoMessage("Código enviado para seu email.");

	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public EsqueceuSenha getEntity() {
		if (entity == null)
			entity = new EsqueceuSenha();
		return entity;
	}

}
