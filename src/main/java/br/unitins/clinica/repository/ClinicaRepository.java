package br.unitins.clinica.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Clinica;

public class ClinicaRepository extends Repository<Clinica> {
	
	@Override
	public Clinica findById(int id) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL ou SQL
			Query query = em.createQuery("SELECT c FROM Clinica c WHERE c.id = :id");
			query.setParameter("id", id);
			
			
			return  (Clinica) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}
	}

}
