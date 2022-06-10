package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Consulta;

public class ConsultaRepository extends Repository<Consulta> {
	
	@SuppressWarnings("unchecked")
	public List<Consulta> findByConsulta() throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" c ");
			jpql.append(" FROM ");
			jpql.append(" Consulta c  ");
			jpql.append(" WHERE ");
			jpql.append(" c.status = 2");

			Query query = getEntityManager().createQuery(jpql.toString());
			
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}
	

}
