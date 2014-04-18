package br.edu.ifms.lp2.aulatestes;

public class VetorUtil {

	public static int[] multiplicaValoresPorDois(int[] vetor) {
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = vetor[i] * 2;
		}
		return vetor;
	}

	public static int[] inverte(int[] vetor) {
		for (int i = 0; i < vetor.length / 2; i++) {
			int j = vetor.length - i - 1;
			int aux = vetor[i];
			vetor[i] = vetor[j];
			vetor[j] = aux;
		}
		return vetor;
	}

	public static int somatorio(int[] vetor) {
		int soma = 0;
		for (int valor : vetor) {
			soma += valor;
		}
		return soma;
	}

	public static int[] ordena(int[] vetor) {
		boolean trocou;
		do {
			trocou = false;
			for (int i = 0; i < vetor.length - 1; i++) {
				int j = i + 1;
				if (vetor[i] > vetor[j]) {
					int aux = vetor[i];
					vetor[i] = vetor[j];
					vetor[j] = aux;
					trocou = true;
				}
			}
		} while (trocou);
		return vetor;
	}
}
