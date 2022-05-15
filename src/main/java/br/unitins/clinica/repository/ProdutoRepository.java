package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Produto;

public class ProdutoRepository extends Repository<Produto>{
	
	@SuppressWarnings("unchecked")
	public List<Produto> findByNome(String descricao) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" p ");
			jpql.append(" FROM ");
			jpql.append(" Produto p  ");
			jpql.append(" WHERE ");
			jpql.append(" p.descricao LIKE :descricao");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("descricao", "%" + descricao + "%");

			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}

}
