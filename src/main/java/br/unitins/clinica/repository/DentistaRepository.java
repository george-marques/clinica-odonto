package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Dentista;

public class DentistaRepository extends Repository<Dentista> {

	@SuppressWarnings("unchecked")
	public List<Dentista> findByAtivos(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" d ");
			jpql.append(" FROM ");
			jpql.append(" Dentista d  ");
			jpql.append(" WHERE ");
			jpql.append(" d.nome LIKE :nome AND d.ativo = true");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Dentista> findByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" d ");
			jpql.append(" FROM ");
			jpql.append(" Dentista d  ");
			jpql.append(" WHERE ");
			jpql.append(" d.nome LIKE :nome ");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}

}
