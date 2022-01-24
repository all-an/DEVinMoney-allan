package entidades;

import servicos.Investimento;

public class ContaInvestimento extends Conta {

	private Investimento investimento;
	private Double valorInvestido;
	
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

	public Double getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(Double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}
	
	public String simulaRendimento() {
		String resposta = investimento.rendimento(valorInvestido);
		return resposta;
	}
}
