package main;

import java.time.LocalDate;
import java.util.Date;

public class Conta {
	private String codigoConta;
	private Date data;
	private Double valorPago;
	private String tipoPagamento;
	
	/**
	 * Conta da fatura do usuário.
	 * 
	 * @param codigoConta - Código da conta.
	 * @param data - Data da compra.
	 * @param valorPago - Valor pago na compra. 
	 * @param tipoPagamento - Tipo de pagamento da compra. 
	 */
	public Conta(String codigoConta, Date data, double valorPago, String tipoPagamento) {
		this.codigoConta = codigoConta;
		this.data = data;
		this.valorPago = valorPago;
		this.tipoPagamento = tipoPagamento;
	}

	/**
	 * Retorna o código da compra do usuário.
	 * 
	 * @return - Retorna o código da conta.
	 */
	public String getCodigoConta() {
		return this.codigoConta;
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
	public String getTipoPagamento() {
		return this.tipoPagamento;
	}
}
