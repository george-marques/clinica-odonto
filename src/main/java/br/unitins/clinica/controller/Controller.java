package br.unitins.clinica.controller;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.Util;
import br.unitins.clinica.application.VersionException;
import br.unitins.clinica.model.DefaultEntity;
import br.unitins.clinica.repository.Repository;

public abstract class Controller<T extends DefaultEntity> {

	private Repository<T> repository;
	protected T entity;

	public Controller() {

	}

	public Controller(Repository<T> repository) {
		super();
		this.repository = repository;
	}

	public void incluir() {
		try {
			limparRelacionamentosNaoObrigatorios();
			setEntity(getRepository().save(getEntity()));
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void alterar(T obj) {
		this.entity = obj;
		incluir();
	}
	
	public void salvarSemLimpar() {
		try {
			limparRelacionamentosNaoObrigatorios();
			setEntity(getRepository().save(getEntity()));
			Util.addInfoMessage("Salvo com sucesso.");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	protected void limparRelacionamentosNaoObrigatorios() {
		
	}

	public void alterar() {
		try {
			limparRelacionamentosNaoObrigatorios();
			setEntity(getRepository().save(getEntity()));
			Util.addInfoMessage("Alteração realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	
	public void excluir() {
		try {
			getRepository().remove(getEntity());
			Util.addInfoMessage("Exclusão realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		entity = null;
	}

	public Repository<T> getRepository() {
		return repository;
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}

}
