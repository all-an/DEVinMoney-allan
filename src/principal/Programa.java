package principal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Conta;
import entidades.ContaCorrente;
import entidades.ContaInvestimento;
import entidades.ContaPoupanca;
import entidades.Transacao;
import servicos.InvestimentoCDB;
import servicos.InvestimentoTesouroDireto;

public class Programa {
	
	
	static Scanner scanner = new Scanner(System.in);
	
	static List<Conta> listaContas = new ArrayList<>();
	static List<Transacao> historicoTransacoes = new ArrayList<>();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Locale.setDefault(new Locale("pt", "BR"));
		
		int sair = 0;
        while(sair == 0) {
            try {
            	System.out.println("------------------------------");
            	System.out.println("Bem vindo ao banco DevInMoney ! "
            			+ "\nEscolha a operacao digitando o numero referente: "
            			+ "\n1) Cadastro \n2) Operacional \n3) Sair");
            	int operacao = scanner.nextInt();
            	if(operacao == 1)
            		cadastro();
            	else if(operacao == 2)
            		operacional();
            	else if(operacao == 3)
            		sair = 1;
            	else {
            		System.out.println("Escolha novamente entre os numeros");
            		continue;
            	}
            }
            catch(InputMismatchException | NumberFormatException ex ) {
                System.out.println("Favor digitar um numero valido");
                scanner.next();
                continue;
            }
            catch(NullPointerException e) {
            	System.out.println("Digite 1 para reiniciar o programa.");
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
			ContaCorrente contaCorrente = new ContaCorrente(nome, cpf, rendaMensal, agencia);
			listaContas.add(contaCorrente);
			System.out.println("Conta criada com sucesso. Conta Número: " + contaCorrente.getConta());
		}else if(contaEscolha == 2) {
			ContaPoupanca contaPoupanca = new ContaPoupanca(nome, cpf, rendaMensal, agencia); 
			listaContas.add(contaPoupanca);
			System.out.println("Conta criada com sucesso. Conta Número: " + contaPoupanca.getConta());
			int sair = 0;
	        while(sair == 0) {
	        	System.out.println("Vamos simular seus rendimentos. \nDigite em quantos meses deseja simular: ");
	        	Integer meses = scanner.nextInt();
	        	System.out.println("Digite a taxa de rentabilidade: ");
	        	Double taxa = scanner.nextDouble();
	            System.out.println(contaPoupanca.simularRentabilidade(meses, taxa));
	        	System.out.println("Para finalizar digite 1 , para simular outra vez digite 0");
	        	sair = scanner.nextInt();
	        }
			System.out.println("Conta criada com sucesso. Conta Número: " + contaPoupanca.getConta());
		}else if(contaEscolha == 3) {
			ContaInvestimento contaInvestment = new ContaInvestimento(nome, cpf, rendaMensal, agencia, null);
			int sair = 0;
	        while(sair == 0) {
	        	System.out.println("Escolha um investimento: \n1) CDB (5% ao ano) \n2) Tesouro Direto (1% ao ano)");
	        	int investm = scanner.nextInt();
	        	if(investm == 1) {
	        		contaInvestment.setInvestimento(new InvestimentoCDB());
	        	}else if(investm == 2) {
	        		contaInvestment.setInvestimento(new InvestimentoTesouroDireto());
	        	}else {
	        		System.out.println("Favor escolher um investimento");
	        		sair = 0;
	        	}
	        	while(sair == 0) {
		        	System.out.println("Vamos simular seus investimentos. \nDigite o valor a ser investido: \nDigite 0 para nao investir no momento.");
		        	Double valorInvest = scanner.nextDouble();
		        	if(valorInvest < contaInvestment.getSaldo()) {
		        		contaInvestment.setValorInvestido(valorInvest);
			        	System.out.println(contaInvestment.simulaRendimento());
		        	}else {
		        		System.out.println("Favor digitar um valor menor que seu saldo.");
		        	}
		        	System.out.println("Para finalizar digite 1 , para simular outra vez digite 0");
		        	sair = scanner.nextInt();
		        }
	        	
	        }
			listaContas.add(contaInvestment);
			System.out.println("Conta criada com sucesso. " + contaInvestment.getConta());
		}
			
		return null;
	}
	
	public static void operacional() throws IOException, InterruptedException {
		// fonte de informação https://www.geeksforgeeks.org/getting-the-date-of-url-connection-in-java/
		// proíbe que o usuário faça transações em dias anteriores alterando a data do sistema:
		URL url = new URL("http://www.google.com"); // setando uma url
		 
        HttpURLConnection httpCon = (HttpURLConnection)url.openConnection(); //abrindo conexão
        long dataUrl = httpCon.getDate(); //buscando e atribuindo a data da url em dataUrl
        Date data = new Date(dataUrl); // pegando a data e criando variável data pela url acima
        
    	ZoneId zonaDefault = ZoneId.systemDefault(); //buscando e atribuindo em zonaDefault o id do time-zone como default do sistema

    	Instant instante = data.toInstant(); // pegando o instante da data e atribuindo a um Instant 
    	
    	LocalDate hoje = instante.atZone(zonaDefault).toLocalDate(); // convertendo a data do instante para localdate
		//
    	
    	
		System.out.print("Digite o numero da sua conta corrente: ");
		
		int numConta = scanner.nextInt();
		Conta conta = listaContas.stream().filter(x -> x.getConta() == numConta).findFirst().orElse(null);
		if(listaContas.contains(conta)) {
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
					System.out.println(contaCorrente.getSaldo());
				}else if(conta.getClass().isAssignableFrom(ContaInvestimento.class)) {
					ContaInvestimento contaInvestimento = (ContaInvestimento) conta;
					System.out.println(contaInvestimento.getExtratoTrancacoes());
					System.out.println(contaInvestimento.getSaldo());
				}
				break;
			case 5:
				if(!confereFimDeSemana(hoje)){
					if(listaContas.contains(conta)) {
						System.out.print("Digite o numero da conta de destino: ");
						int numContaDest = scanner.nextInt();
						Conta contaDestino = listaContas.stream().filter(x -> x.getConta() == numContaDest).findFirst().orElse(null);
						System.out.println("Digite o valor a ser transferido: ");
						Double valorATransferir = scanner.nextDouble();
						conta.transferir(contaDestino, valorATransferir);
						historicoTransacoes.add(new Transacao(contaDestino, conta, valorATransferir, new Date()));
						break;
					}else {
						System.out.println("Conta destino nao existe");
						break;
					}
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
		}else {
			System.out.println("Conta nao existe");
		}
	}
	
	// Trecho de código que foi pedida a autorização de uso ao professor no Slack (dia 24/01/21 9:21 AM)
	// Encontrado neste site  shorturl.at/jtvIJ
	public static boolean confereFimDeSemana(final LocalDate ld) // passa a data atual como parâmetro e travada no dia e hora atual
    {
        DayOfWeek dia = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK)); // pega o dia da semana desta data e atribui a uma variável dia
        return dia == DayOfWeek.SUNDAY || dia == DayOfWeek.SATURDAY; // retorna um booleano caso seja dia da semana ou não
    }
	
}
