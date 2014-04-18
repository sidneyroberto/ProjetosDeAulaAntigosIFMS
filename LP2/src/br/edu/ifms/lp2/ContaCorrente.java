package br.edu.ifms.lp2;

import java.util.Scanner;

public class ContaCorrente extends ContaBancaria {

	private double limite;

	@Override
	public double sacar(double valorASacar) {
		if (valorASacar <= (saldo + limite)) {
			saldo -= valorASacar;
		}
		return saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public static void main(String[] args) {
		System.out.println("Informe o valor a:");
		Scanner leitor = new Scanner(System.in);
		String a = leitor.nextLine();
		System.out.println("Informe o valor b:");
		String b = leitor.nextLine();
		System.out.println("A: " + a + "\tB: " + b);
	}
}
