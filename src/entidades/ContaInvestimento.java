package entidades;

import java.util.Date;

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
		if(valorInvestido != 0)
			extratoTrancacoes.put(new Date(), "Investiu " + valorInvestido);
			saque(valorInvestido);
		return resposta;
	}
	
	@Override
	public String toString() {
		return "Conta Poupanca [nome= " + nome + ", cpf= " + getCpf() + ", rendaMensal= " + rendaMensal + ", agencia= "
				+ agencia + "]";
	}
}
