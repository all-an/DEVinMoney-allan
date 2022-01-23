package principal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entidades.Conta;
import entidades.ContaCorrente;
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
            			+ "\nEscolha a opera��o digitando o n�mero referente: "
            			+ "\n1) Cadastro \n2) Opera��o \n3) Sair");
            	int operacao = scanner.nextInt();
            	if(operacao == 1)
            		cadastro();
            	else if(operacao == 2)
            		operacional();
            	else if(operacao == 3)
            		sair = 1;
            	else {
            		System.out.println("Digite uma op��o v�lida");
            		continue;
            	}
            }
            catch(InputMismatchException | NumberFormatException ex ) {
                System.out.println("Favor digitar um n�mero v�lido");
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
				System.out.println("Conta inv�lida e removida");
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
		System.out.println("Digite a ag�ncia: \n001) Florian�polis \n002) S�o Jos�");
		Integer agencia = scanner.nextInt();
		System.out.println("Digite o tipo de conta que deseja: "
				+ "\n1) Conta Corrente"
				+ "\n2) Conta Poupan�a"
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
		Transacao transacao = new Transacao();
		System.out.print("Digite o n�mero da sua conta corrente: ");
		int numConta = scanner.nextInt();
		Conta conta = listaContas.stream().filter(x -> x.getConta() == numConta).findFirst().orElse(null);
		System.out.println(conta.getSaldo());
		conta.saque(300.00);
		if(conta.getClass().isAssignableFrom(ContaCorrente.class)) {
			ContaCorrente contaCorrente = (ContaCorrente) conta;
			System.out.print(contaCorrente.getExtratoTrancacoes());
			contaCorrente.getSaldo();
		}
	}
}
