package servicos;

public class InvestimentoTesouroDireto implements Investimento {

	private Double valorInvestido;
	
	public InvestimentoTesouroDireto() {
	}
	
	public InvestimentoTesouroDireto(Double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}
	
	@Override
	public String rendimento() {
		// TODO Auto-generated method stub 1%
		return null;
	}

}
