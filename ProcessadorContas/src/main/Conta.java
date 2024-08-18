package main;

import java.util.Date;


public class Conta {
	private final String codigoConta;
	private final Date data;
	private final Double valorPago;
	private final TipoPagamento tipoPagamento;
	
	/**
	 * Conta da fatura do usuário.
	 * 
	 * @param codigoConta - Código da conta.
	 * @param data - Data da compra.
	 * @param valorPago - Valor pago na compra. 
	 * @param tipoPagamento - Tipo de pagamento da compra. 
	 */
	public Conta(String codigoConta, Date data, double valorPago, TipoPagamento tipoPagamento) {
		this.codigoConta = codigoConta;
		this.data = data;
		this.valorPago = valorPago;
		this.tipoPagamento = tipoPagamento;
	}

	/**
	 * Retorna a data da compra feita pelo usuário
	 * .
	 * @return - Retorna a data da compra.
	 */
	public Date getData() {
		return this.data;
	}
	
	/**
	 * Retorna o valor total da compra do usuário.
	 * 
	 * @return Retorna o valor total da compra do usuario. 
	 */
	public Double getValorPago() {
		return this.valorPago;
	}
	
	/**
	 * Retorna o tipo de pagamento.
	 * 
	 * @param Retorna o tipo de pagamento realizado.
	 */
	public TipoPagamento getTipoPagamento() {
		return this.tipoPagamento;
	}
}