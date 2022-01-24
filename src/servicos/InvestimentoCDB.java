package servicos;

public class InvestimentoCDB implements Investimento {

	private Double valorInvestido;
	
	public InvestimentoCDB() {
	}
	
	public InvestimentoCDB(Double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	@Override
	public String rendimento() {
		// TODO Auto-generated method stub 5%
		return null;
	}

}
