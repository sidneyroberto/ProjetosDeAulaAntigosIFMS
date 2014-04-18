package br.edu.ifms.lp2;

import java.util.Scanner;

public class ExemploConversao {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		Double[] numeros = new Double[3];
		for (int i = 0; i < numeros.length; i++) {
			System.out.println("Digite um número real");
			System.out
					.println("(utilizando ponto ou vírgula para separar as casas decimais): ");
			String numeroStr = leitor.next();
			numeros[i] = 0D;
			try {
				numeros[i] = Double.parseDouble(numeroStr);
			} catch (NumberFormatException e) {
				numeroStr = numeroStr.replace(",", ".");
				numeros[i] = Double.parseDouble(numeroStr);
			} finally {
				numeros[i] = Math.pow(numeros[i], 2);
			}
		}
		leitor.close();

		for (int i = 0; i < numeros.length; i++) {
			System.out
					.println("Resultado (nro " + (i + 1) + "): " + numeros[i]);
		}
	}
}
