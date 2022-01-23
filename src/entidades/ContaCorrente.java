package entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContaCorrente extends Conta {
	
	private Double chequeEspecial;
	private Map<Date, String> extratoTrancacoes;
	
	public ContaCorrente(String nome, String cpf, Double rendaMensal,Integer agencia) {
		super(nome, cpf, rendaMensal, agencia);
		this.chequeEspecial = rendaMensal;
		this.extratoTrancacoes = new HashMap<Date, String>();
		extratoTrancacoes.put(new Date(), rendaMensal.toString() + "\n");
	}

	public Double getChequeEspecial() {
		return chequeEspecial;
	}

	public void setChequeEspecial(Double chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}

	public Map<Date, String> getExtratoTrancacoes() {
		System.out.println("\nExtrato de Transações:");
		return extratoTrancacoes;
	}

	public void setExtratoTrancacoes(Map<Date, String> extratoTrancacoes) {
		this.extratoTrancacoes = extratoTrancacoes;
	}
	
	@Override
	public void saque(Double valor) {
		saldo -= valor;
		extratoTrancacoes.put(new Date(), " -" + valor + "\n");
		
	}
	
}
