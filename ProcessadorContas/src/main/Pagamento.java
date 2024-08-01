package main;

import java.util.Date;

public class Pagamento {
	private final Double valorPago;
	private final Date dataPagamento;
	private final TipoPagamento tipoPagamento;
	
	/**
	 * Pagamento da conta.
	 * 
	 * @param valorPago - Valor pago da conta. 
	 * @param dataPagamento - Data de pagamento da conta.
	 * @param tipoPagamento - Tipo de pagamento da conta. 
	 */
	public Pagamento(Double valorPago, Date dataPagamento, TipoPagamento tipoPagamento) {
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
	public TipoPagamento getTipoPagamento() {
		return this.tipoPagamento;
	}
}