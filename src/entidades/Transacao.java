package entidades;

import java.util.Date;

public class Transacao {

	private Conta dadosContaOrigem;
	private Conta dadosContaDestino;
	private Double valor;
	private Date data;
	
	public Transacao() {
	}
	
	public Transacao(Conta dadosContaOrigem, Conta dadosContaDestino, Double valor, Date data) {
		this.dadosContaOrigem = dadosContaOrigem;
		this.dadosContaDestino = dadosContaDestino;
		this.valor = valor;
		this.data = data;
	}

	public Conta getDadosContaOrigem() {
		return dadosContaOrigem;
	}

	public void setDadosContaOrigem(Conta dadosContaOrigem) {
		this.dadosContaOrigem = dadosContaOrigem;
	}

	public Conta getDadosContaDestino() {
		return dadosContaDestino;
	}

	public void setDadosContaDestino(Conta dadosContaDestino) {
		this.dadosContaDestino = dadosContaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Histórico Transação: \n[Dados Conta Origem=" + dadosContaOrigem.toString() + ", Dados Conta Destino=" + dadosContaDestino.toString()
				+ ", valor=" + valor + ", data=" + data + "]" + "\n- - - - - - - - - - - - - -";
	}
	
	
	
}
