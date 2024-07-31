package main;

import java.util.Date;

public class Pagamento {
	private Double valorPago;
	private Date dataPagamento;
	private String tipoPagamento;
	
	/**
	 * Pagamento da conta.
	 * 
	 * @param valorPago - Valor pago da conta. 
	 * @param dataPagamento - Data de pagamento da conta.
	 * @param tipoPagamento - Tipo de pagamento da conta. 
	 */
	public Pagamento(Double valorPago, Date dataPagamento, String tipoPagamento) {
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
		this.tipoPagamento = tipoPagamento;
	}
	
	/**
	 * Retorna o valor pago da conta.
	 * 
	 * @return Retorna o valor pago da conta. 
	 */
	public Double getValorPago() {
		return this.valorPago;
	}
	
	/**
	 * Retorna a data de pagamento da conta.
	 * 
	 * @return Retorna a data de pagamento.
	 */
	public Date getDataPagamento() {
		return this.dataPagamento;
	}
	
	/**
	 * Retorna o tipo de pagamento da conta.
	 * 
	 * @return Retorna o tipo de pagamento da conta. 
	 */
	public String getTipoPagamento() {
		return this.tipoPagamento;
	}
}
