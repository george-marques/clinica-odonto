package br.unitins.clinica.model;

import java.util.ArrayList;
import java.util.List;

public enum Perfil {
	ADMINISTRADOR(1, "Administrador"), FUNCIONARIO(2, "Funcionário");

	private int id;
	private String label;
	private List<String> paginasComPermissao;

	Perfil(int id, String label) {
		this.id = id;
		this.label = label;
		paginasComPermissao = new ArrayList<String>();
		if (id == 1) {
			paginasComPermissao.add("/Clinica/faces/pages/cidade.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/cidadelistingsql.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/consulta.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/clinica.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/consultalisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/estado.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/estadolisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/dentista.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/dentistalisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/paciente.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/pacientelisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/produto.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/registro.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/relatorioatendimento.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/tipo-atendimento.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/tipoatendimentolisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/vendalisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/gerenciar-usuarios.xhtml");
			
		
		} else {
			paginasComPermissao.add("/Clinica/faces/pages/cidade.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/cidadelistingsql.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/consulta.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/consultalisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/estado.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/estadolisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/dentistalisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/paciente.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/pacientelisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/produto.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/registro.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/tipoatendimentolisting.xhtml");
			paginasComPermissao.add("/Clinica/faces/pages/vendalisting.xhtml");
			
			paginasComPermissao.add("/Clinica/faces/pages/relatorioatendimento.xhtml");
		}

	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public List<String> getPaginasComPermissao() {
		return paginasComPermissao;
	}

	public static Perfil valueOf(int id) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getId() == id)
				return perfil;
		}
		return null;
	}

}
