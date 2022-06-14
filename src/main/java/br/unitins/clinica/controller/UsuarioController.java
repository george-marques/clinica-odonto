package br.unitins.clinica.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.model.Perfil;
import br.unitins.clinica.model.PessoaFisica;
import br.unitins.clinica.model.Sexo;
import br.unitins.clinica.model.Usuario;
import br.unitins.clinica.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = -4714515783891349751L;
	private InputStream fotoInputStream = null;
	private List<Usuario> listaUsuario;
	private String filtro;

	public UsuarioController() {
		super(new UsuarioRepository());
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public void pesquisar() {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			setListaUsuario(repo.findByLogin(filtro));
		} catch (RepositoryException e) {
			setListaUsuario(null);
		}
	}

	@Override
	public void limpar() {
		setListaUsuario(null);
		super.limpar();
	}

	public void editar(Usuario user) {
		setEntity(user);
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

	public void incluirNovo() {
		super.incluir();
	}

	@Override
	public void incluir() {
		getEntity().setSenha(Util.hash(getEntity()));
		getEntity().setPerfil(Perfil.FUNCIONARIO);

		super.salvarSemLimpar();

		// caso exista uma imagem
		if (getFotoInputStream() != null) {
			// salvando a imagem
			if (!Util.saveImageUsuario(getFotoInputStream(), "png", getEntity().getId())) {
				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
				return;
			}
		}
		limpar();
	}

	@Override
	public void alterar() {
		getEntity().setSenha(Util.hash(getEntity()));

		super.salvarSemLimpar();

		// caso exista uma imagem
		if (getFotoInputStream() != null) {
			// salvando a imagem
			if (!Util.saveImageUsuario(getFotoInputStream(), "png", getEntity().getId())) {
				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
				return;
			}
		}
		limpar();
	}

	@Override
	public Usuario getEntity() {
		if (entity == null) {
			entity = new Usuario();
			entity.setPessoaFisica(new PessoaFisica());
		}

		return entity;
	}

	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioRepository pr = new UsuarioRepository();
			listaUsuario = pr.listarTodos(entity);
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}