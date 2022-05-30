package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Cidade;

public class CidadeRepository extends Repository<Cidade> {

	@SuppressWarnings("unchecked")
	public List<Cidade> findByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  c ");
			jpql.append("FROM ");
			jpql.append("  Cidade c ");
			jpql.append("WHERE ");
			jpql.append("  c.nome LIKE :nome ");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome.");
		}
	}


	public List<Object[]> findByNomeSQL(String nome) throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  	c.id, ");
			sql.append("	c.nome, ");
			sql.append("	e.nome as nomeEstado, ");
			sql.append("	e.sigla ");
			sql.append("FROM ");
			sql.append("  Cidade c, ");
			sql.append("  Estado e ");
			sql.append("WHERE ");
			sql.append("  c.id_estado = e.id ");
			sql.append("  AND c.nome LIKE :nome ");

			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNomeSQL.");
		}
	}

}
