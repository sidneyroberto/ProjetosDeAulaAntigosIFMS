package br.edu.ifms.lp2;

public class ContaBancaria {

	protected Cliente cliente;

	protected String numeroConta;

	protected double saldo;

	public double sacar(double valorASacar) {
		if (valorASacar <= saldo) {
			saldo -= valorASacar;
		}
		return saldo;
	}

	public double depositar(double valorADepositar) {
		if (valorADepositar > 0) {
			saldo += valorADepositar;
		}
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}
}
