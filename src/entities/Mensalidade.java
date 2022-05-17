package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.enums.Situation;

public class Mensalidade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3997271522632552024L;

	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date dataDeVencimento;
	private Date dataDePagamento;
	private Double valor;
	private Situation situation;

	public Mensalidade() {
	}

	public Mensalidade(Date dataDeVencimento, Date dataDePagamento, Double valor) {
		this.dataDeVencimento = dataDeVencimento;
		this.dataDePagamento = dataDePagamento;
		this.valor = valor;
		this.situation = Situation.PAGA;
	}
	
	public Date getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(Date dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

	public Date getDataDePagamento() {
		return dataDePagamento;
	}

	public void setDataDePagamento(Date dataDePagamento) {
		this.dataDePagamento = dataDePagamento;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Situation getSituation() {
		
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	@Override
	public String toString() {
		return "Data de Pagamento: " + sdf.format(dataDePagamento)
		+ ", Data de Vencimento: "+ sdf.format(dataDeVencimento)+ ", Status da Mensalidade: " + situation + ", Valor: " + valor;
	}

	
}
