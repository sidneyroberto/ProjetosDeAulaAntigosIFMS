package br.edu.ifms.lp2;

import java.util.Scanner;

public class LeituraInteiro {

	public static void main(String[] args) {
		System.out.println("Digite um numero inteiro:");
		boolean numeroOk = false;
		do {
			Scanner leitor = new Scanner(System.in);
			try {
				int numero = leitor.nextInt();
				System.out.println("Numero informado: " + numero);
				numeroOk = true;
			} catch (Exception e) {
				System.out.println("O valor informado nao e inteiro!");
				System.out.println("Digite novamente o numero:");
			}
		} while (!numeroOk);

	}

}
