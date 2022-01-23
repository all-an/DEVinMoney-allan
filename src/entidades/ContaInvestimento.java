package entidades;

import servicos.Investimento;

public class ContaInvestimento extends Conta {

	private Investimento investimento;
	
	public ContaInvestimento(String nome, String cpf, Double rendaMensal, Integer agencia, Investimento investimento) {
		super(nome, cpf, rendaMensal, agencia);
		this.investimento = investimento;
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}

	
}
