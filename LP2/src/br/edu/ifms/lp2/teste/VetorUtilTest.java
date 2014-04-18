package br.edu.ifms.lp2.teste;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import br.edu.ifms.lp2.aulatestes.VetorUtil;

public class VetorUtilTest {

	private final int NUMERO_TESTES = 10000;

	private final int TAMANHO_VETOR = 10000;

	private int[] vetor;

	private int[] vetorInvertido;

	private int[] vetorOrdenado;

	private int[] vetorMultiplicadoPorDois;

	private int somatorio;

	public void inicializaVetores() {
		long semente = System.currentTimeMillis();
		Random sorteador = new Random(semente);
		int tamanho = sorteador.nextInt(TAMANHO_VETOR) + 1;
		vetor = new int[tamanho];
		vetorInvertido = new int[tamanho];
		vetorOrdenado = new int[tamanho];
		vetorMultiplicadoPorDois = new int[tamanho];
		somatorio = 0;
		for (int i = 0; i < vetor.length; i++) {
			int j = vetor.length - i - 1;
			vetor[i] = sorteador.nextInt();
			vetorInvertido[j] = vetor[i];
			vetorOrdenado[i] = vetor[i];
			vetorMultiplicadoPorDois[i] = vetor[i] * 2;
			somatorio += vetor[i];
		}
		Arrays.sort(vetorOrdenado);
	}

	@Test
	public void testaMultiplicacaoValoresPorDois() {
		for (int i = 0; i < NUMERO_TESTES; i++) {
			inicializaVetores();
			vetor = VetorUtil.multiplicaValoresPorDois(vetor);
			Assert.assertArrayEquals(vetor, vetorMultiplicadoPorDois);
		}
	}

	@Test
	public void testaSomatorioValoresVetor() {
		for (int i = 0; i < NUMERO_TESTES; i++) {
			inicializaVetores();
			int somatorio = VetorUtil.somatorio(vetor);
			Assert.assertEquals(somatorio, this.somatorio);
		}
	}

	@Test
	public void testaOrdenacaoVetor() {
		for (int i = 0; i < NUMERO_TESTES; i++) {
			inicializaVetores();
			vetor = VetorUtil.ordena(vetor);
			Assert.assertArrayEquals(vetor, vetorOrdenado);
		}
	}

	@Test
	public void testaInversaoVetor() {
		for (int i = 0; i < NUMERO_TESTES; i++) {
			inicializaVetores();
			vetor = VetorUtil.inverte(vetor);
			Assert.assertArrayEquals(vetor, vetorInvertido);
		}
		int[] outroVetor = new int[4];
		int[] maisOutroVetor = new int[4];
		outroVetor = VetorUtil.inverte(outroVetor);
		Assert.assertArrayEquals(outroVetor, maisOutroVetor);
	}

}
