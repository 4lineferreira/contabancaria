package conta.model;

public class ContaPoupanca extends Conta {

	private int aniversario;

	public ContaPoupanca(int numero, int agencia, int tipo, String titular, float saldo, int aniversario) {
		super(numero, agencia, tipo, titular, saldo);
		this.aniversario = aniversario;

	}

	public int getAniversario() {
		return aniversario;
	}

	public void setAniversario(int aniversario) {
		this.aniversario = aniversario;
	}

	@Override
	public void Visualizar() {
		super.Visualizar();
		System.out.println("Limite de crédito " + this.aniversario);

	}

}
