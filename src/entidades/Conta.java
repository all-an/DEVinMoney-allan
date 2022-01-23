package entidades;

import java.util.Arrays;

public class Conta {
	
	private int[] cpf;

	public Conta(String cpf) {
		int[] cpfIntArr = new int[cpf.length()];
		for (int i = 0; i < cpf.length(); i++)
		{
		    cpfIntArr[i] = cpf.charAt(i) - '0'; // '0' char é 48 em decimal https://www.asciitable.com/
		    System.out.println("Convertido para int: " + cpfIntArr[i]);
		}
		System.out.println(validaCpf(cpfIntArr));
		this.cpf = cpfIntArr;
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
			System.out.println("Cpf inválido");
			return false;
		}else if(validadorGovBrDigitoUm(cpf) == false || validadorGovBrDigitoDois(cpf) == false) {
			System.out.println("Digito Verificador INVÁLIDO, cpf inválido");
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Conta [cpf=" + Arrays.toString(cpf) + "]";
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
		System.out.println("Soma um: " + soma);
		System.out.println("Resto um: " + resto);
		//return digUmVerdadeiro.equals(cpf[9]);
		System.out.println(digUmVerdadeiro);
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
		System.out.println(digDoisVerdadeiro);
		return digDoisVerdadeiro.equals(cpf[10]);
	}
}
