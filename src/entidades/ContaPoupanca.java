package entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContaPoupanca extends Conta {

	private Integer meses;
	private Double rentabilidAnual;
	private Map<Date, String> extratoTrancacoes;
		
	public ContaPoupanca(String nome, String cpf, Double rendaMensal, Integer agencia) {
		super(nome, cpf, rendaMensal,agencia);
		this.extratoTrancacoes = new HashMap<Date, String>();
		extratoTrancacoes.put(new Date(), rendaMensal.toString());
	}

	public Integer getMeses() {
		return meses;
	}

	public void setMeses(Integer meses) {
		this.meses = meses;
	}

	public Double getRentabilidAnual() {
		return rentabilidAnual;
	}

	public void setRentabilidAnual(Double rentabilidAnual) {
		this.rentabilidAnual = rentabilidAnual;
	}

	public Map<Date, String> getExtratoTrancacoes() {
		System.out.println("\nExtrato de Transações:");
		return extratoTrancacoes;
	}

	public void setExtratoTrancacoes(Map<Date, String> extratoTrancacoes) {
		this.extratoTrancacoes = extratoTrancacoes;
	}
	
	public String simularRentabilidade(Integer meses, Double taxaRentabilidade) {
		Double taxaMes = ((Math.pow((1.000 + (taxaRentabilidade / 100.000)),(1.000 / 12.000))) - 1.000) * 100.000;
		Double valorAcumulado = saldo * Math.pow((1 + taxaMes), meses);
		System.out.println(valorAcumulado);
		String rendimentos = "Rendimentos: " + String.format("%.2f", valorAcumulado) + ", ao total de " + meses + " meses.";
		return  rendimentos;
	}
}
