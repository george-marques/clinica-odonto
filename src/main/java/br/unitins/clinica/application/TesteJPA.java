package br.unitins.clinica.application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.unitins.clinica.model.Consulta;
import br.unitins.clinica.model.TipoAtendimento;
import br.unitins.clinica.repository.ConsultaRepository;

public class TesteJPA {
	public static void main(String[] args) {
		// teste de selecao em uma tabela para gerar o banco de dados.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Clinica");
		EntityManager em = emf.createEntityManager();

		TipoAtendimento tipo1 = new TipoAtendimento();
		tipo1.setNome("Teste3");
		TipoAtendimento tipo2 = new TipoAtendimento();
		tipo2.setNome("teste4");

		List<TipoAtendimento> listaTipo = new ArrayList<TipoAtendimento>();
		listaTipo.add(tipo1);
		listaTipo.add(tipo2);

		Consulta c1 = new Consulta();
		c1.setId(7);
		c1.setListaTipoAtendimento(listaTipo);

		ConsultaRepository rp = new ConsultaRepository();

		try {
			c1 = rp.save(c1);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO SALVE");
			e.printStackTrace();
		} catch (VersionException e) {
			e.printStackTrace();
		}

	
		System.out.println("feito.");
	}
}
