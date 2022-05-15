package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.PessoaFisica;

public class PessoaFisicaRepository extends Repository<PessoaFisica> {
	
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> findByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" pf ");
			jpql.append(" FROM ");
			jpql.append(" PessoaFisica pf");
			jpql.append(" WHERE ");
			jpql.append(" pf.nome LIKE :nome AND pf.id = p.id");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}
}
