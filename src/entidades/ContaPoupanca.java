package entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContaPoupanca extends Conta {

	private Integer meses;
	private Double rentabilidAnual;
	private Map<Date, String> extratoTrancacoes;
		
	public ContaPoupanca(String nome, String cpf, Double rendaMensal, Integer agencia, Integer meses,
			Double rentabilidAnual, Map<Date, String> extratoTrancacoes) {
		super(nome, cpf, rendaMensal,agencia);
		this.meses = meses;
		this.rentabilidAnual = rentabilidAnual;
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
		return extratoTrancacoes;
	}

	public void setExtratoTrancacoes(Map<Date, String> extratoTrancacoes) {
		this.extratoTrancacoes = extratoTrancacoes;
	}
	
	

}
