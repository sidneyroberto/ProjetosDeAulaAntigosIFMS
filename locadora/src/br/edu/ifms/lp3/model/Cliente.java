package br.edu.ifms.lp3.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries(value = {
		@NamedQuery(name = Cliente.LISTAR_TODOS, query = "FROM Cliente c ORDER BY c.nome"),
		@NamedQuery(name = Cliente.LISTAR_TODOS_POR_NOME, query = "FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(:nome) ORDER BY c.nome") })
@Entity
public class Cliente {

	public static final String LISTAR_TODOS = "Cliente.listarTodos";

	public static final String LISTAR_TODOS_POR_NOME = "Cliente.listarTodosPorNome";

	@Id
	private String cpf;

	private String nome;

	private String endereco;

	private String sexo;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
