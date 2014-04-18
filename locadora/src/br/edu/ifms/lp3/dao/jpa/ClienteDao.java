package br.edu.ifms.lp3.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.lp3.model.Cliente;
import br.edu.ifms.lp3.util.JPAUtil;

public class ClienteDao extends JPADao<Cliente> {

	public Cliente recupera(Serializable id) {
		try {
			em = JPAUtil.getEntityManager();
			String cpf = String.valueOf(id);
			Cliente objeto = (Cliente) em.find(Cliente.class, cpf);
			em.close();
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			em = JPAUtil.getEntityManager();
			Query query = em.createNamedQuery(Cliente.LISTAR_TODOS);
			clientes = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.isOpen()) {
				em.close();
			}
		}
		return clientes;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaTodosPorNome(String nome) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			em = JPAUtil.getEntityManager();
			Query query = em.createNamedQuery(Cliente.LISTAR_TODOS_POR_NOME);
			query.setParameter("nome", "%" + nome + "%");
			clientes = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.isOpen()) {
				em.close();
			}
		}
		return clientes;
	}

}
