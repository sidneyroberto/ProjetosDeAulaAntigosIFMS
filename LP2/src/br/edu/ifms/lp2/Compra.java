package br.edu.ifms.lp2;

import java.util.Date;

public class Compra {

	private Date dataCompra;

	private Produto[] produtosComprados;

	public Compra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public void adicionaProduto(Produto produto) {
		// Deveria adicionar o produto na lista de produtos...
	}

	public Produto removeProduto(String descricao) {
		/*
		 * Deveria remover o produto com a descri��o igual � passada por
		 * par�metro e retornar o objeto do produto removido...
		 */
		return null;
	}
}
