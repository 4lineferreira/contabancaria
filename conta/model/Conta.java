package conta.model;

import conta.util.Cores;

public abstract class Conta {

	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo;

	public Conta(int numero, int agencia, int tipo, String titular, float saldo) {

		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;

	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean Sacar(float valor) {

		if (this.getSaldo() < valor) {
			System.out.println("\n Saldo Insuficiente");
			return false;
		}

		this.setSaldo(this.getSaldo() - valor);
		return true;
	}

	public void Depositar(float valor) {
		this.setSaldo(this.getSaldo() + valor);
	}

	public void Visualizar() {

		String tipo = "";

		switch (this.tipo) {
		case 1:
			tipo = "Conta corrente";
			break;
		case 2:
			tipo = "Conta poupança";
			break;
		}
		System.out.println(Cores.TEXT_RED_BOLD + "\n\n***********************************************************");
		System.out.println(Cores.TEXT_WHITE_BOLD + "Dados da conta: ");
		System.out.println(Cores.TEXT_RED_BOLD + "***********************************************************");
		System.out.println(Cores.TEXT_WHITE_BOLD + "Número da conta: " + this.numero);
		System.out.println(Cores.TEXT_WHITE_BOLD + "Agência: " + this.agencia);
		System.out.println(Cores.TEXT_WHITE_BOLD + "Titular: " + this.titular);
		System.out.println(Cores.TEXT_WHITE_BOLD + "Saldo: " + this.saldo);

	}

}
