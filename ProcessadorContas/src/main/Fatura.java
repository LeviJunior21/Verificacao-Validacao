package main;

import java.util.Date;

public class Fatura {
	private final Date dataVencimento;
	private final Double valorFatura;
	private final String nomeUsuario;
	private StatusPagamento statusPagamento;
	
	/**
	 * Fatura do cliente.
	 * 
	 * @param dataVencimento - Data de vencimento da fatura.
	 * @param valorFatura - Valor da fatura.
	 * @param nomeUsuario - Nome do usuário. 
	 */
	public Fatura(Date dataVencimento, Double valorFatura, String nomeUsuario) {
		this.dataVencimento = dataVencimento;
		this.valorFatura = valorFatura;
		this.nomeUsuario = nomeUsuario;
		this.statusPagamento = StatusPagamento.PENDENTE;
	}
	
	/**
	 * Retorna a data de vencimento da fatura.
	 * 
	 * @return retorna a data de vencimento da fatura do usuário. 
	 */
	public Date getDataVencimento() {
		return this.dataVencimento;
	}
	
	/**
	 * Retorna o valor da fatura a ser pago.
	 * 
	 * @return Retorna o valor da fatura.
	 */
	public Double getValorFatura() {
		return this.valorFatura;
	}
	
	/**
	 * Retorna o nome do usuário que irá pagar a fatura.
	 * 
	 * @return Retorna o nome do usuário.
	 */
	public String getNomeUsuario() {
		return this.nomeUsuario;
	}
	
	/**
	 * Retorna o status da fatura como PENDENTE ou PAGA.
	 * 
	 * @return Retorna o status da fatura. 
	 */
	public StatusPagamento getStatusPagamento() {
		return this.statusPagamento;
	}
	
	/**
	 * Altera o status da fatura.
	 * 
	 * @param status - Novo status da Fatura.
	 */
	public void setStatus(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
}
