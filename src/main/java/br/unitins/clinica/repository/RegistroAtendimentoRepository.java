package br.unitins.clinica.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.RegistroAtendimento;

public class RegistroAtendimentoRepository extends Repository<RegistroAtendimento> {

	public List<RegistroAtendimento> findByDentista(String nome) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  r ");
			jpql.append("FROM ");
			jpql.append("  RegistroAtendimento r, Consulta c, Dentista d ");
			jpql.append("WHERE ");
			jpql.append("  r.consulta = c.id AND ");
			jpql.append("  c.dentista = d.id AND ");
			jpql.append("  d.nome LIKE :nome");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome.");
		}
	}

}
