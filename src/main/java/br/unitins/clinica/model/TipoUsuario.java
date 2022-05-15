package br.unitins.clinica.model;

public enum TipoUsuario {

	ADMINISTRADOR(1, "Administrador"), ATENDENTE(2, "Atendente");

	private int id;
	private String label;

	TipoUsuario(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static TipoUsuario valueOf(int id) {
		for (TipoUsuario tipo : TipoUsuario.values()) {
			if (tipo.getId() == id)
				return tipo;
		}
		return null;
	}

}
