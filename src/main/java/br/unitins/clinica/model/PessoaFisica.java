package br.unitins.clinica.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class PessoaFisica extends Pessoa {
	@CPF(message = "Informe um CPF válido.")
	private String cpf;
	private Sexo sexo;
	@NotNull(message = "Informe sua data de nascimento.")
	@Past(message = "Informe uma data anterior ao dia de hoje.")
	private LocalDate dataNascimento;

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
