package br.edu.ifms.lp2;

import java.util.ArrayList;
import java.util.List;

public class CadastroProduto {

	private List<Produto> listaProdutos = new ArrayList<Produto>();

	public void incluirProduto(String descricao, double preco) {
		if (preco > 0D) {
			Produto produto = new Produto();
			produto.setDescricao(descricao);
			produto.setPreco(preco);
			listaProdutos.add(produto);
		}
	}
}
