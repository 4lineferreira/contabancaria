package conta.model;

public class ContaCorrente extends Conta {

	private float limite;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);

		this.limite = limite;

	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	@Override
	public boolean Sacar(float valor) {
		if (this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\n Saldo Insuficiente");
			return false;
		}
		this.setSaldo(this.getSaldo() - valor);
		return true;
	}

	@Override
	public void Visualizar() {
		super.Visualizar();
		System.out.println("Limite de crédito " + this.limite);
	}

}
