package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Paciente;

public class PacienteRepository extends Repository<Paciente> {
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" p ");
			jpql.append(" FROM ");
			jpql.append(" Paciente p ");
			jpql.append(" WHERE ");
			jpql.append(" p.pessoaFisica.nome LIKE :nome");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao consultar");
		}
	}
	
	
	

}
