package br.edu.ifms.lp2;

public class Fibonacci {

	public void fibonacci(long n) {
		fibonacci(n, 0, 1);
	}

	private void fibonacci(long n, long valor, long anterior) {
		if (n > 0) {
			System.out.print(valor + " ");
			long aux = valor;
			valor += anterior;
			anterior = aux;
			fibonacci(n - 1, valor, anterior);
		}
	}

}
