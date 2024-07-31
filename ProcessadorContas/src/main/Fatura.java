package main;

import java.util.Date;

public class Fatura {
	private Date dataVencimento;
	private Double valorFatura;
	private String nomeUsuario;
	private String status;
	
	/**
	 * Fatura do cliente.
	 * 
	 * @param dataVencimento - Data de vencimento da fatura.
	 * @param i - Valor da fatura.
	 * @param nomeUsuario - Nome do usuário. 
	 */
	public Fatura(Date dataVencimento, Double valorFatura, String nomeUsuario) {
		this.dataVencimento = dataVencimento;
		this.valorFatura = valorFatura;
		this.nomeUsuario = nomeUsuario;
		this.status = "PENDENTE";
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
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Altera o status da fatura.
	 * 
	 * @param status - Novo status da Fatura.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
