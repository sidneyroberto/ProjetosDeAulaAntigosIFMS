package br.edu.ifms.lp2;

public abstract class FuncoesMatematicas {

	public static double PI = 3.141592653589793;

	public static double NUMERO_DE_EULER = 2.718281828459045235360287;

	public static double NUMERO_DE_OURO = 1.61803398874989484820458683436563811;

	private FuncoesMatematicas() {
	}

	public static double delta(double a, double b, double c) {
		return Math.pow(b, 2) - 4 * a * c;
	}

	public static int fatorial(int n) {
		int resultado = 1;
		for (int i = 2; i <= n; i++) {
			resultado *= i;
		}
		return resultado;
	}

}
