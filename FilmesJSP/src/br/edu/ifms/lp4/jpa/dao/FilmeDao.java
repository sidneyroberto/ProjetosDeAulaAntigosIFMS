package br.edu.ifms.lp4.jpa.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.lp4.model.Filme;
import br.edu.ifms.lp4.util.JPAUtil;

public class FilmeDao extends JPADao<Filme> {

	@Override
	public Filme recupera(Serializable id) {
		try {
			em = JPAUtil.getEntityManager();
			String cpf = String.valueOf(id);
			Filme objeto = (Filme) em.find(Filme.class, cpf);
			em.close();
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Filme> recuperaTodos() {
		List<Filme> filmes = new ArrayList<Filme>();
		try {
			em = JPAUtil.getEntityManager();
			Query query = em.createNamedQuery(Filme.LISTAR_TODOS);
			filmes = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.isOpen()) {
				em.close();
			}
		}
		return filmes;
	}

}
