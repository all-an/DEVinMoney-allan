package entidades;

public class ContaCorrente extends Conta {
	
	private Double chequeEspecial;
	
	public ContaCorrente(String nome, String cpf, Double rendaMensal,Integer agencia) {
		super(nome, cpf, rendaMensal, agencia);
		this.chequeEspecial = rendaMensal;
	}

	public Double getChequeEspecial() {
		return chequeEspecial;
	}

	public void setChequeEspecial(Double chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}
	
}
