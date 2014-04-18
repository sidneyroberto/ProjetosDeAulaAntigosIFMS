package br.edu.ifms.lp2;

import java.util.Calendar;

public class ContaPoupanca extends ContaBancaria {

	private int diaDeRendimento;

	public double calcularNovoSaldo(double taxaRendimento) {
		int diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		if (diaDeRendimento == diaAtual) {
			saldo += saldo * (taxaRendimento / 100D);
		}
		return saldo;
	}

	public int getDiaDeRendimento() {
		return diaDeRendimento;
	}

	public void setDiaDeRendimento(int diaDeRendimento) {
		this.diaDeRendimento = diaDeRendimento;
	}

	public static void main(String[] args) {
		ContaPoupanca conta = new ContaPoupanca();
		conta.setDiaDeRendimento(1);
		conta.depositar(1000);
		System.out.println(conta.calcularNovoSaldo(0.5));
	}
}
