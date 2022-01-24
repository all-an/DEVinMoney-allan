package entidades;

import java.util.Arrays;

public abstract class Conta {
	
	private String nome;
	private int[] cpf;
	private Double rendaMensal;
	private static int conta = 0;
	private Integer agencia;
	protected Double saldo;

	public Conta(String nome, String cpf, Double rendaMensal,Integer agencia) {
		this.nome = nome;
		if(validaCpf(converteParaIntArr(cpf))) {
			this.cpf = converteParaIntArr(cpf);
		}else {
			this.cpf = null;
		}
		this.rendaMensal = rendaMensal;
		this.conta += 1;
		if(agencia == 001 || agencia == 002) {
			this.agencia = agencia;
		}else {
			System.out.println("Voc� digitou uma ag�ncia inv�lida, vamos refazer o cadastro");
		}
		this.saldo = rendaMensal;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public int getConta() {
		return conta;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public String getCpf() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < cpf.length; i++) {
		    stringBuilder.append(cpf[i]);
		}
		String cpfString = stringBuilder.toString();
		return cpfString;
	}

	public Boolean validaCpf(int[] cpf) {
		if(cpf.length != 11) {
			System.out.println("Cpf inv�lido");
			return false;
		}else if(validadorGovBrDigitoUm(cpf) == false || validadorGovBrDigitoDois(cpf) == false) {
			System.out.println("Digito Verificador INV�LIDO, cpf inv�lido");
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Conta [cpf=" + Arrays.toString(cpf) + "]";
	}
	
	//Segundo c�lculo neste site https://www.somatematica.com.br/faq/cpf.php
	public Boolean validadorGovBrDigitoUm(int[] cpf) {
		Integer digUmVerdadeiro = null;
		Integer soma = 0;
		for(int i = 10; i > 1; i--) {
			soma += cpf[Math.abs(i - 10)] * i;
		}
		Integer resto = soma % 11;
		if(resto == 0 || resto == 1) {
			digUmVerdadeiro = 0;
		}else if(resto >= 2 && resto <= 10) {
			digUmVerdadeiro = 11 - resto;
		}
		return digUmVerdadeiro.equals(cpf[9]);
	}
	
	public Boolean validadorGovBrDigitoDois(int[] cpf) {
		Integer digDoisVerdadeiro = null;
		Integer soma = 0;
		for(int i = 11; i > 1; i--) {
			soma += cpf[Math.abs(i - 11)] * i;
		}
		Integer resto = soma % 11;
		if(resto == 0 || resto == 1) {
			digDoisVerdadeiro = 0;
		}else if(resto >= 2 && resto <= 10) {
			digDoisVerdadeiro = 11 - resto;
		}
		return digDoisVerdadeiro.equals(cpf[10]);
	}
	
	public int[] converteParaIntArr(String cpf) {
		int[] cpfIntArr = new int[cpf.length()];
		for (int i = 0; i < cpf.length(); i++)
		{
		    cpfIntArr[i] = cpf.charAt(i) - '0'; // '0' char � 48 em decimal https://www.asciitable.com/
		}
		return cpfIntArr;
	}
	
	public void saque(Double valor) {
		saldo -= valor;
	}
	
	public void deposito(Double valor) {
		saldo += valor;
	}

	public String transferir(Conta conta, Double valor) {
		if(conta.getConta() != this.getConta()) {
			saldo -= valor;
			conta.deposito(valor);
			return "Transfer�ncia realizada com sucesso !";
		}else if(valor > conta.getSaldo()){
			return "Voc� n�o tem saldo!";
		}else {
			return "� proibido transferir para si pr�prio !";
		}
	}
	
}
