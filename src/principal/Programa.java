package principal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import entidades.Conta;
import entidades.ContaCorrente;
import entidades.ContaPoupanca;
import entidades.Transacao;

public class Programa {
	static Scanner scanner = new Scanner(System.in);
	
	static List<Conta> listaContas = new ArrayList<>();
	static List<Transacao> historicoTransacoes = new ArrayList<>();
	
	public static void main(String[] args) {
		
		int sair = 0;
        while(sair == 0) {
            try {
            	System.out.println("------------------------------");
            	System.out.println("Bem vindo ao banco DevInMoney ! "
            			+ "\nEscolha a operacao digitando o numero referente: "
            			+ "\n1) Cadastro \n2) Operacao \n3) Sair");
            	int operacao = scanner.nextInt();
            	if(operacao == 1)
            		cadastro();
            	else if(operacao == 2)
            		operacional();
            	else if(operacao == 3)
            		sair = 1;
            	else {
            		System.out.println("Digite uma opção valida");
            		continue;
            	}
            }
            catch(InputMismatchException | NumberFormatException ex ) {
                System.out.println("Favor digitar um numero válido");
                scanner.next();
                continue;
            }
            catch(NullPointerException e) {
            	System.out.println("Favor reiniciar o programa.");
                scanner.next();
                continue;
            }
        }
		
		for(Conta c : listaContas) {
			if(c.getCpf() == null) {
				listaContas.remove(c);
				System.out.println("Conta invalida e removida");
			}
		}
		
		System.out.println(listaContas.get(0).getConta());
	}
	
	public static String cadastro() {
    	System.out.println("Digite o nome: ");	
		String nome = scanner.next();
		System.out.println("Digite o cpf: ");	
		String cpf = scanner.next();
		System.out.println("Digite a renda mensal: ");
		Double rendaMensal = scanner.nextDouble();
		System.out.println("Digite a agencia: \n001) Florianopolis \n002) Sao Jose");
		Integer agencia = scanner.nextInt();
		System.out.println("Digite o tipo de conta que deseja: "
				+ "\n1) Conta Corrente"
				+ "\n2) Conta Poupanca"
				+ "\n3) Conta Investimento ");
		int contaEscolha = scanner.nextInt();
		if(contaEscolha == 1) {
			listaContas.add(new ContaCorrente(nome, cpf, rendaMensal, agencia));
			System.out.println("Conta criada com sucesso.");
		}
			
		
			//if(!containsId(listaContas, id)) {
				//listaContas.add(new Employee(id, name, salary));
			//}
		return null;
	}
	
	public static void operacional() {
		LocalDate hoje = LocalDate.now();
		
		LocalDate someDate = LocalDate.of(2021, 1, 23); // 2nd-Jan-2021
		
		System.out.print("Digite o numero da sua conta corrente: ");
		int numConta = scanner.nextInt();
		Conta conta = listaContas.stream().filter(x -> x.getConta() == numConta).findFirst().orElse(null);
		System.out.println("Digite operacao que deseja: "
				+ "\n1) Saque \n2) Deposito \n3) Saldo \n4) Extrato \n5) Transferir \n6) Alterar Dados Cadastrais");
		
		int operacao = scanner.nextInt();
		switch(operacao) {
			case 1:
				System.out.println("Digite o valor a ser sacado: ");
				Double valorSaque = scanner.nextDouble();
				conta.saque(valorSaque);
				historicoTransacoes.add(new Transacao(conta, conta,valorSaque, new Date()));
				System.out.println(conta.getSaldo());
				break;
			case 2:
				
					System.out.println("Digite o valor a ser depositado: ");
					Double valorDeposito = scanner.nextDouble();
					conta.deposito(valorDeposito);
					historicoTransacoes.add(new Transacao(conta, conta, valorDeposito, new Date()));
					System.out.println(conta.getSaldo());
					break;					
				
			case 3:
				System.out.print("Saldo: " + conta.getSaldo());
				break;
			case 4:
				if(conta.getClass().isAssignableFrom(ContaCorrente.class) || conta.getClass().isAssignableFrom(ContaPoupanca.class)) {
					ContaCorrente contaCorrente = (ContaCorrente) conta;
					System.out.print(contaCorrente.getExtratoTrancacoes());
					contaCorrente.getSaldo();
				}
				break;
			case 5:
				if(!confereFimDeSemana(someDate)){
					System.out.print("Digite o numero da conta de destino: ");
					int numContaDest = scanner.nextInt();
					Conta contaDestino = listaContas.stream().filter(x -> x.getConta() == numContaDest).findFirst().orElse(null);
					System.out.println("Digite o valor a ser transferido: ");
					Double valorATransferir = scanner.nextDouble();
					conta.transferir(contaDestino, valorATransferir);
					historicoTransacoes.add(new Transacao(contaDestino, conta, valorATransferir, new Date()));
					break;
				}else {
					System.out.println("Transacao proibida aos fins de semana");
					break;
				}
			case 6:
				System.out.println("Digite o dado da conta que deseja alterar: "
						+ "\n1) Nome \n2) Renda Mensal \n3) Agencia");
				int dadoConta = 0;
				if(dadoConta == 1) {
					System.out.println("Digite o novo Nome: ");
					conta.setNome(scanner.next());
				}else if(dadoConta == 2) {
					System.out.println("Digite a nova Renda Mensal: ");
					conta.setRendaMensal(scanner.nextDouble());
				}else if(dadoConta == 3) {
					System.out.println("Digite a nova Agencia (001 ou 002) \nFavor nao digitar uma agencia invalida"
							+ ", senao programa sera reiniciado");
					conta.setAgencia(scanner.nextInt());
				}
				break;
		}
	}
	
	// Trecho de código que foi pedida a autorização de uso ao professor no Slack (dia 24/01/21 9:21 AM)
	// Encontrado neste site  shorturl.at/jtvIJ
	public static boolean confereFimDeSemana(final LocalDate ld) // passa a data atual como parâmetro e travada no dia e hora
    {
        DayOfWeek dia = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK)); // pega o dia da semana desta data e atribui a uma variável dia
        return dia == DayOfWeek.SUNDAY || dia == DayOfWeek.SATURDAY; // retorna um booleano caso seja dia da semana ou não
    }
}
