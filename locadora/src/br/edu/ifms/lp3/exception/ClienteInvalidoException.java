package br.edu.ifms.lp3.exception;

@SuppressWarnings("serial")
public class ClienteInvalidoException extends Exception {

	public ClienteInvalidoException(String mensagemErro) {
		super(mensagemErro);
	}
}
