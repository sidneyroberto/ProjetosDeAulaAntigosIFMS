package br.edu.ifms.lp3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name = Compra.LISTA_COMPRAS_DO_CLIENTE, query = "FROM Compra c WHERE c.cliente.cpf = :cpf")
@Entity
public class Compra {

	public static final String LISTA_COMPRAS_DO_CLIENTE = "Compra.listaComprasDoCliente";

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Produto produto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
