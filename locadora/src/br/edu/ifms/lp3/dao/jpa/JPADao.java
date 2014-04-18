package br.edu.ifms.lp3.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.ifms.lp3.dao.Dao;
import br.edu.ifms.lp3.util.JPAUtil;

public abstract class JPADao<T> implements Dao<T> {

	protected EntityManager em;

	public T salva(T objeto) {
		try {
			em = JPAUtil.getEntityManager();
			EntityTransaction transacao = em.getTransaction();
			transacao.begin();
			objeto = em.merge(objeto);
			transacao.commit();
			em.close();
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return null;
	}

	public boolean remove(T objeto) {
		try {
			em = JPAUtil.getEntityManager();
			EntityTransaction transacao = em.getTransaction();
			transacao.begin();
			objeto = em.merge(objeto);
			em.remove(objeto);
			transacao.commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return false;
	}
}
