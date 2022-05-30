package br.unitins.clinica.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Session;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.application.VersionException;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.repository.UsuarioRepository;

@Named
@SessionScoped
public class PerfilUsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = -5860103774810988150L;
	private InputStream fotoInputStream = null;
	private String senha;
	private String confirmarSenha;
	private String senhaAtual;

	public PerfilUsuarioController() {
		super(new UsuarioRepository());
	}

	public void excluirConta() {
		excluir();
		Util.redirect("login.xhtml");
	}

	public void alterarUsuario() {
		UsuarioRepository ur = new UsuarioRepository();
		
		System.out.println("Senha no banco: " + getEntity().getSenha());
		setSenhaAtual(Util.hash(getEntity().getLogin() + getSenhaAtual()));
		System.out.println("senha informada " + getSenhaAtual());

		if (getEntity().getSenha().equals(getSenhaAtual()) && getConfirmarSenha().equals(getSenha())) {
			try {

				if (!getSenha().isEmpty()) {
					getEntity().setSenha(senha);
					getEntity().setSenha(Util.hash(getEntity()));

				}

				try {
					ur.save(entity);
				} catch (VersionException e) {

					e.printStackTrace();
				}

				if (getFotoInputStream() != null) {
					// salvando a imagem
					if (!Util.saveImageUsuario(getFotoInputStream(), "png", getEntity().getId())) {
						Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
						return;
					}
				}

				Util.addInfoMessage("Alteração realizada com sucesso.");
			} catch (RepositoryException e) {
				Util.addErrorMessage("Problema ao salvar, tente novamente ou entre em contato com a TI.");
				return;
			}
		} else {

			Util.addErrorMessage("Verifique a confirmação de senha e tente novamente.");
			return;
		}

		limpar();
		setSenha("");
		setConfirmarSenha("");
		
	}


	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}

	@Override
	public Usuario getEntity() {
		// Obtendo o usuário da sessao
		if (entity == null) {
			entity = (Usuario) Session.getInstance().get("usuarioLogado");
		}
		return entity;
	}

	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

}
