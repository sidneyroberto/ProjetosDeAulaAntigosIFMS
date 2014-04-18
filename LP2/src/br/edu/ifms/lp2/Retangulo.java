package br.edu.ifms.lp2;

public class Retangulo {

	private double base;

	private double altura;

	public Retangulo() {
		// TODO Auto-generated constructor stub
	}

	public Retangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}

	public double calculaArea() {
		return base * altura;
	}

	public static double calculaArea(double base, double altura) {
		return base * altura;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

}
