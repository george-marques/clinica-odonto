package br.unitins.clinica.repository;

import javax.persistence.Query;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.TipoUsuario;
import br.unitins.clinica.model.Usuario;

public class UsuarioRepository extends Repository<Usuario> {
	
	public Usuario verificarUsuario(TipoUsuario tipo, String senha) throws RepositoryException{
		try { 
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  u ");
			jpql.append("FROM ");
			jpql.append("  Usuario u ");
			jpql.append("WHERE ");
			jpql.append(" u.tipoUsuario = :tipo ");
			jpql.append(" AND u.senha = :senha");
			
			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("tipoUsuario", tipo);
			query.setParameter("senha", senha);
			
			
			return (Usuario) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o verificarUsuario.");
		}
	}

}
