package br.edu.ifms.lp3.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.ifms.lp3.model.Cliente;

public class TesteClienteDao {

	public Cliente atualiza(Cliente cliente) {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("default");
			em = emf.createEntityManager();
			cliente = em.merge(cliente);
			em.close();
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (em != null) {
			em.close();
		}
		return null;
	}
}
