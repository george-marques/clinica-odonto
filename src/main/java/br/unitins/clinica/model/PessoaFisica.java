package br.unitins.clinica.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Entity
public class PessoaFisica extends Pessoa {

	@NotBlank(message = "O CPF deve ser informado")
	private String cpf;

	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
