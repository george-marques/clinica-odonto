package br.unitins.clinica.model;

public enum Status {

	ATENDIDO(1, "Atendido"), AGUARDANDO(2, "Aguardando");

	private int id;
	private String label;

	Status(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static Status valueOf(int id) {
		for (Status status : Status.values()) {
			if (status.getId() == id)
				return status;
		}
		return null;
	}

}
