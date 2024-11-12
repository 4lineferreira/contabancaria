package conta;

import java.io.IOException;
import java.util.InputMismatchException;

import java.util.Scanner;
import conta.util.Cores;
import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {

	public static void main(String[] args) {

		ContaController contas = new ContaController();

		Scanner scanner = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "**************************************************************");
			System.out.println("                                                              ");
			System.out.println("                      BANCO DO BRAZIL COM Z                   ");
			System.out.println("                                                              ");
			System.out.println("**************************************************************");
			System.out.println("                                                              ");
			System.out.println("                                                              ");
			System.out.println("            1 - Criar conta                                   ");
			System.out.println("            2 - Listar todas as contas                        ");
			System.out.println("            3 - Buscar contas por número                      ");
			System.out.println("            4 - Atualizar dados da contas                     ");
			System.out.println("            5 - Apagar conta                                  ");
			System.out.println("            6 - Sacar                                         ");
			System.out.println("            7 - Depositar                                     ");
			System.out.println("            8 - Transferir valores entre contas               ");
			System.out.println("            9 - Sair                                          ");
			System.out.println("                                                              ");
			System.out.println("                                                              ");
			System.out.println("**************************************************************");
			System.out.println("Entre com a opção desejada:                                   ");
			System.out.println("                                                              " + Cores.TEXT_RESET);

			// opcao = scanner.nextInt();

			try {
				opcao = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				scanner.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(
						Cores.TEXT_WHITE_BOLD + "Banco do Brazil com Z - Simples, rápido e inovador só que com Z ");
				sobre();
				scanner.close();
				System.exit(0);

			}
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar conta\n\n");

				System.out.println("Digite o número da agência: ");
				agencia = scanner.nextInt();
				scanner.skip("\\R?");
				System.out.println("Digite o nome do titular: ");
				titular = scanner.nextLine();

				do {
					System.out.println("Digite o número da agência: (1-CC ou 2-CP): ");
					tipo = scanner.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o saldo da conta (R$): ");
				saldo = scanner.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o ccrédito (R$): ");
					limite = scanner.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do aniversário da conta: ");
					aniversario = scanner.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todos as contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da conta\n\n");

				System.out.println("Digite o número da conta: ");
				numero = scanner.nextInt();

				contas.procurarPorNumrto(numero);

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da conta \n\n");

				System.out.println(" Digite o número da conta: ");
				numero = scanner.nextInt();

				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {

					tipo = buscaConta.getTipo();

					System.out.println(" Digite o número da agência: ");
					agencia = scanner.nextInt();
					System.out.println(" Digite o nome do titular: ");
					scanner.skip("\\R?");
					titular = scanner.nextLine();

					System.out.println(" Digite o saldo da conta (R$): ");
					saldo = scanner.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println(" Digite o limite de crédito (R$): ");
						limite = scanner.nextFloat();

						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println(" Digite o dia do Aniversário da conta: ");
						aniversario = scanner.nextInt();

						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println(" Tipo de conta inválida!");
					}
					}

				} else {
					System.out.println("A conta não foi encontrada!");
				}

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a conta\n\n");

				System.out.println("Digite o número da conta :");
				numero = scanner.nextInt();

				contas.deletar(numero);

				keyPress();
				break;

			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");

				System.out.println("Digite o número da conta: ");
				numero = scanner.nextInt();

				do {
					System.out.println("Digite o valor do saque (R$): ");
					valor = scanner.nextFloat();
				} while (valor <= 0);

				contas.sacar(numero, valor);

				keyPress();
				break;

			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");

				System.out.println("Digite o número da conta: ");
				numero = scanner.nextInt();
				
				do {
					System.out.println("Digite o valor do depósito (R$): ");
					valor = scanner.nextFloat();
				}while(valor <=0);
				
				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre contas\n\n");
				
				System.out.println("Digite o número conta de origem: ");
				numero = scanner.nextInt();
				System.out.println("Digite o número da conta destino: ");
				numeroDestino = scanner.nextInt();
				
				do {
					System.out.println("Digite o valor da transferência (R$): ");
					valor = scanner.nextFloat();
				}while(valor <=0);
				
				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção invalida\n");

				keyPress();
				break;
			}

		}

	}

	public static void kayPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n pressione Enter para continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("você pressionou uma tecla difererte de enter!");
		}
	}

	public static void sobre() {
		System.out.println("\n**************************************************************");
		System.out.println("*                                                            *");
		System.out.println("*                  Projeto Desenvolvido por:                 *");
		System.out.println("*         Aline Ferreira - alineferreirasnsd@gmail.com       *");
		System.out.println("*        https://github.com/4lineferreira/contabancaria      *");
		System.out.println("*                                                            *");
		System.out.println("**************************************************************");
	}

	public static void keyPress() {

		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
}
