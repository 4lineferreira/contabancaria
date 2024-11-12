package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.respository.ContaRepository;

public class ContaController implements ContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumrto(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null)
			conta.Visualizar();
		else
			System.out.println("\nAc conta número: " + numero + "não foi encontrada!");

	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.Visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\n Conta número :" + conta.getNumero() + "foi criada com sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {
		var buscarConta = buscarNaCollection(conta.getNumero());
		
		if (buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), conta);
			System.out.println("\nA conta número : " + conta.getNumero() + " Foi atualizada com sucesso!");
		}else 
			System.out.println("\nA conta número: " + conta.getNumero() + " não foi encontrada!");

	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.println("\nA conta número : " + numero + "foi deletada com sucesso! ");
		} else
			System.out.println("\nA conta número :" + numero + " não foi encontrada!");

	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if (conta.Sacar(valor)==true)
				System.out.println("\no saque na conta número: " + numero + " foi efetuado com sucesso!");
		}else System.out.println(" \nA conta número: " + numero + " não foi encontrada!");

	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			conta.Depositar(valor);
			System.out.println("\nO deósito na conta número: " + numero + " foi efetuado com sucesso!");
		}else
			System.out.println("\nA conta número: " + numero + " não foi encontrada ou a conta destino não é uma conta CORRENTE!");

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if(contaOrigem != null && contaDestino != null) {
			if (contaOrigem.Sacar(valor)== true) {
				System.out.println("\nA transferência foi efetuada com sucesso!");
			}
		}else
			System.out.println("\nA conta de origem e/ou destino não foram encontradas! ");

	}

	public int gerarNumero() {
		return ++numero;
	}

	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}

		return null;
	}
}
