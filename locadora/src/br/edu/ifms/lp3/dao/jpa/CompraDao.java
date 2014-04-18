package br.edu.ifms.lp3.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.lp3.model.Cliente;
import br.edu.ifms.lp3.model.Compra;
import br.edu.ifms.lp3.util.JPAUtil;

public class CompraDao extends JPADao<Compra> {

	@Override
	public Compra recupera(Serializable id) {
		try {
			em = JPAUtil.getEntityManager();
			Integer idCompra = (Integer) id;
			Compra objeto = (Compra) em.find(Compra.class, idCompra);
			em.close();
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Compra> listaComprasDoCliente(Cliente cliente) {
		List<Compra> compras = new ArrayList<Compra>();
		if (cliente != null && cliente.getCpf() != null) {
			try {
				em = JPAUtil.getEntityManager();
				Query query = em
						.createNamedQuery(Compra.LISTA_COMPRAS_DO_CLIENTE);
				query.setParameter("cpf", cliente.getCpf());
				compras = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		em.close();
		return compras;
	}
}
