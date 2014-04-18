package br.edu.ifms.lp2.excecao;

@SuppressWarnings("serial")
public class DataInvalidaException extends Exception {

	public DataInvalidaException() {
		super("A data de nascimento deve ser anterior ou igual à data atual.");
	}
}
