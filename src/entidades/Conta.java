package entidades;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Conta {
	
	protected String nome;
	private int[] cpf;
	protected Double rendaMensal;
	private static int conta = 0;
	protected Integer agencia;
	protected Double saldo;
	protected Map<Date, String> extratoTrancacoes;

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
			System.out.println("Você digitou uma agência inválida, vamos refazer o cadastro");
		}
		this.saldo = rendaMensal;
		this.extratoTrancacoes = new HashMap<Date, String>();
		extratoTrancacoes.put(new Date(), "Cheque Especial " + rendaMensal.toString() + "\n");
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
	
	public Map<Date, String> getExtratoTrancacoes() {
		return extratoTrancacoes;
	}

	public void setExtratoTrancacoes(Map<Date, String> extratoTrancacoes) {
		this.extratoTrancacoes = extratoTrancacoes;
	}


	public Boolean validaCpf(int[] cpf) {
		if(cpf.length != 11) {
			System.out.println("Cpf inválido");
			return false;
		}else if(validadorGovBrDigitoUm(cpf) == false || validadorGovBrDigitoDois(cpf) == false) {
			System.out.println("Digito Verificador INVÁLIDO, cpf inválido");
			return false;
		}
		return true;
	}

	
	
	//Segundo cálculo neste site https://www.somatematica.com.br/faq/cpf.php
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
		    cpfIntArr[i] = cpf.charAt(i) - '0'; // '0' char é 48 em decimal https://www.asciitable.com/
		}
		return cpfIntArr;
	}
	
	public void saque(Double valor) {
		saldo -= valor;
		extratoTrancacoes.put(new Date(), " -" + valor + "\n");
	}
	
	public void deposito(Double valor) {
		saldo += valor;
		extratoTrancacoes.put(new Date(), " +" + valor + "\n");
	}

	public String transferir(Conta conta, Double valor) {
		if(conta.getConta() != this.getConta()) {
			saldo -= valor;
			conta.deposito(valor);
			extratoTrancacoes.put(new Date(), " -" + valor + "\n");
			return "Transferência realizada com sucesso !";
		}else if(valor > conta.getSaldo()){
			return "Você não tem saldo!";
		}else {
			return "É proibido transferir para si próprio !";
		}
	}


	@Override
	public String toString() {
		return "Conta [nome=" + nome + ", cpf=" + getCpf() + ", rendaMensal=" + rendaMensal + ", agencia="
				+ agencia + "]";
	}
}
