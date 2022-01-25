package servicos;


public class InvestimentoTesouroDireto implements Investimento {

	public InvestimentoTesouroDireto() {
	}
	
	@Override
	public String rendimento(Double valor) {
		Double taxaMes = 0.01;
		Double rendimentoAno = valor * Math.pow((1 + taxaMes), 12);
		String rendimentos = "Retorno do Investimento: " + String.format("%.2f", rendimentoAno) + ", em um ano.";
		return  rendimentos;
	}

}
