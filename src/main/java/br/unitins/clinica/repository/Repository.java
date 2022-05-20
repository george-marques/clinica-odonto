package br.unitins.clinica.repository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;

import br.unitins.clinica.application.JPAUtil;
import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.application.VersionException;
import br.unitins.clinica.model.DefaultEntity;

public class Repository<T extends DefaultEntity> {

	private EntityManager entityManager;

	public Repository() {
		super();
		setEntityManager(JPAUtil.getEntityManager());
	}

	public T save(T entity) throws RepositoryException, VersionException{
		try {
			getEntityManager().getTransaction().begin();
			entity = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			return entity;
		} catch (OptimisticLockException e) {
			// excecao do @version
			System.out.println("Problema com o controle de concorrencia.");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new VersionException("As informações estão antigas, dê um refresh.");			
		} catch (Exception e) {
			System.out.println("Problema ao executar o save.");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RepositoryException("Problema ao salvar.");
		}

	}

	public void save(T... entitys) throws RepositoryException {
		try {
			getEntityManager().getTransaction().begin();
			for (T entity : entitys) {
				getEntityManager().merge(entity);
			}
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Problema ao executar o save.");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RepositoryException("Problema ao salvar.");
		}
	}

	public void remove(T entity) throws RepositoryException {
		try {
			getEntityManager().getTransaction().begin();
			T obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Problema ao executar o save.");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RepositoryException("Problema ao salvar.");
		}
	}

	public T findById(int id) throws RepositoryException {
		try {
			// obtendo o tipo da classe de forma generica (a classe deve ser publica)
			final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
			
			T t = (T) getEntityManager().find(tClass, id);
			
			return t;
		} catch (Exception e) {
			System.out.println("Erro ao executar o mÃ©todo de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os dados");
		}
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos(T entity) {
		List<T> consulta = new ArrayList<>();
		try {
			getEntityManager().getTransaction().begin();
			Query query = getEntityManager().createQuery("Select o FROM " + entity.getClass().getSimpleName() + " o");

			consulta = query.getResultList();
			getEntityManager().getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Problema ao executar o listar.");
			e.printStackTrace();
		}

		return consulta;

	}
}
