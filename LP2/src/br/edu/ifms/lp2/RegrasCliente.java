package br.edu.ifms.lp2;

import java.util.Date;

import br.edu.ifms.lp2.excecao.DataInvalidaException;

public class RegrasCliente {

	public Cliente cadastraCliente(String cpf, String nome, String endereco,
			Date dataNascimento) throws DataInvalidaException {
		if (dataNascimento != null && !dataNascimento.after(new Date())) {
			Cliente cliente = new Cliente();
			cliente.setCpf(cpf);
			cliente.setNome(nome);
			cliente.setEndereco(endereco);
			cliente.setDataNascimento(dataNascimento);
			return cliente;
		} else {
			throw new DataInvalidaException();
		}
	}
}
