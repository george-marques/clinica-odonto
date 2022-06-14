package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Usuario;

public class UsuarioRepository extends Repository<Usuario> {
	

	public Usuario validarLogin(String login, String senha) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			
			return  (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}
		
	}
	
	
	public Usuario findByEmail(String email) throws RepositoryException {
		try { 
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  u ");
			jpql.append("FROM ");
			jpql.append("  Usuario u ");
			jpql.append("WHERE ");
			jpql.append("  u.pessoaFisica.email = :email ");
			
			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("email", email);
			
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByEmail.");
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findByLogin(String login) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" u ");
			jpql.append(" FROM ");
			jpql.append(" Usuario u ");
			jpql.append(" WHERE ");
			jpql.append(" u.login LIKE :login AND u.ativo = true");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("login", "%" + login + "%");

			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}
	
	
	public Usuario findBySenha(String senha) throws RepositoryException {
		try { 
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  u.senha ");
			jpql.append("FROM ");
			jpql.append("  Usuario u ");
			jpql.append("WHERE ");
			jpql.append("  u.senha = :senha ");
			
			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("senha", senha);
			
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findBySenha.");
		}		
	}
	
	
	public String validarSenha(String login, String senha) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			
			return  (String) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao validar senha.");
		}
		
	}

}
