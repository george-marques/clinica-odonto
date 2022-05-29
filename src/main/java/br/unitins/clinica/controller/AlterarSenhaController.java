package br.unitins.clinica.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.application.VersionException;
import br.unitins.clinica.model.EsqueceuSenha;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.repository.EsqueceuSenhaRepository;
import br.unitins.clinica.repository.UsuarioRepository;

@Named
@ViewScoped
public class AlterarSenhaController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = 6901062364166773594L;
	private String codigo = null;
	private String senha = "";
	private String confirmarSenha;
	private EsqueceuSenhaRepository esqueceuRepo = new EsqueceuSenhaRepository();
	private UsuarioRepository usuarioRepo = new UsuarioRepository();
	private EsqueceuSenha esqueceu = new EsqueceuSenha();
	private boolean retorno = false;

	public AlterarSenhaController() {
		super(new UsuarioRepository());
		validarCodigo();
	}

	@Override
	public Usuario getEntity() {
		if (entity == null) {
			entity = new Usuario();
		}
		return entity;
	}

	public boolean validarCodigo() {
		try {
			setEsqueceu(esqueceuRepo.validarCodigo(codigo));
			if (getEsqueceu() != null) {
				Util.addInfoMessage("Código validado com sucesso!");
				getEsqueceu().setUtilizado(true);
				setRetorno(true);
				return true;
			} else {
				Util.addErrorMessage("Erro, Código inválido!");
				setCodigo(null);
			}
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao validar o seu código");
			e.printStackTrace();
		}
		return false;
	}

	public void redefinirSenha() {
		try {
			if (getSenha().equals(getConfirmarSenha())) {
				if (isRetorno() == false) {
					return;
				}
				setEntity(esqueceuRepo.buscaCodigoUsuario(getCodigo()));
				if (getEntity().getId() != null) {
					getEntity().setSenha(senha);
					getEntity().setSenha(Util.hash(getEntity()));

					try {
						esqueceuRepo.save(getEsqueceu());
					} catch (RepositoryException e) {
						Util.addErrorMessage("Erro, Problema ao desabilitar o código!");
						e.printStackTrace();
					} catch (VersionException e) {
						Util.addErrorMessage("Erro, Código alterado, gere um novo código!");
						e.printStackTrace();
					}
					// Redefinir a senha
					try {
						if (getSenha().equals(getConfirmarSenha())) {
							usuarioRepo.save(getEntity());
							Util.addInfoMessage("Senha alterada com sucesso!");
							Util.redirect("login.xhtml");
						}else {
							Util.addErrorMessage("As senhas não são iguais!");
						}

					} catch (VersionException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao alterar a senha!");
			e.printStackTrace();
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public EsqueceuSenhaRepository getEsqueceuRepo() {
		return esqueceuRepo;
	}

	public void setEsqueceuRepo(EsqueceuSenhaRepository esqueceuRepo) {
		this.esqueceuRepo = esqueceuRepo;
	}

	public UsuarioRepository getUsuarioRepo() {
		return usuarioRepo;
	}

	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	public EsqueceuSenha getEsqueceu() {
		return esqueceu;
	}

	public void setEsqueceu(EsqueceuSenha esqueceu) {
		this.esqueceu = esqueceu;
	}

	public boolean isRetorno() {
		return retorno;
	}

	public void setRetorno(boolean retorno) {
		this.retorno = retorno;
	}

}
