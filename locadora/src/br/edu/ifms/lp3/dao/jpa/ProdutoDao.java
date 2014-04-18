package br.edu.ifms.lp3.dao.jpa;

import java.io.Serializable;

import br.edu.ifms.lp3.model.Produto;
import br.edu.ifms.lp3.util.JPAUtil;

public class ProdutoDao extends JPADao<Produto> {

	@Override
	public Produto recupera(Serializable id) {
		try {
			em = JPAUtil.getEntityManager();
			Integer idProduto = (Integer) id;
			Produto objeto = (Produto) em.find(Produto.class, idProduto);
			em.close();
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return null;
	}

}
