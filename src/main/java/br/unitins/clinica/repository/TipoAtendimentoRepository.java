package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.TipoAtendimento;

public class TipoAtendimentoRepository extends Repository<TipoAtendimento> {

	@SuppressWarnings("unchecked")
	public List<TipoAtendimento> findByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" t ");
			jpql.append(" FROM ");
			jpql.append(" TipoAtendimento t  ");
			jpql.append(" WHERE ");
			jpql.append(" t.nome LIKE :nome");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoAtendimento> findByAtivos(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" t ");
			jpql.append(" FROM ");
			jpql.append(" TipoAtendimento t  ");
			jpql.append(" WHERE ");
			jpql.append(" t.nome LIKE :nome AND t.ativo = true");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}
	
	
	
}
