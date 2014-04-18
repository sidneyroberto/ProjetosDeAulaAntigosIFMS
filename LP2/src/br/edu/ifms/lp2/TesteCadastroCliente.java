package br.edu.ifms.lp2;

import java.util.Calendar;
import java.util.Date;

import br.edu.ifms.lp2.excecao.DataInvalidaException;

public class TesteCadastroCliente {

	public static void main(String[] args) {
		RegrasCliente regras = new RegrasCliente();
		String cpf = "11111111111";
		String nome = "João da Silva";
		String endereco = "Rua 14 de agosto, 23, Vila Inara";
		Calendar calendario = Calendar.getInstance();
		calendario.set(2015, 7, 28);
		Date dataNascimento = calendario.getTime();
		try {
			Cliente cliente = regras.cadastraCliente(cpf, nome, endereco,
					dataNascimento);
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
	}
}
