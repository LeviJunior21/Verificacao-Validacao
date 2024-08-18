package main;

import java.util.Date;
import java.util.List;

public class ProcessadorContas {
	
	/**
	 * Processador de contas com respeito a faturas.
	 * 
	 * @param fatura - Fatura a ser paga.
	 * @param contas - Contas pendentes. 
	 */
	public void processarContas(Fatura fatura, List<Conta> contas) {
		Double valorTotalPagar = 0.0;
		
		for (Conta conta: contas) {
			Pagamento pagamento = this.realizarPagamento(conta, fatura.getDataVencimento());
			
			if (pagamento != null) {
				if (this.pagamentoValido(pagamento, fatura.getDataVencimento())) {
					valorTotalPagar += pagamento.getValorPago();
				}
			}
		}
		System.out.print(valorTotalPagar);
		validarPagamento(valorTotalPagar, fatura);
	}
	
	/**
	 * Validador de pagamento. 
	 * Se a soma total dos valores totais pagos é maior que o valor da fatura, então a fatura é considerada como PAGA.
	 * Já a soma total dos valores totais pagos é menor que o valor da fatura, então a fatura é considerada como PENDENTE.
	 * 
	 * @param valorTotalPagar - Valor total a pagar das contas.
	 * @param fatura - Fatura a ser paga. 
	 */
	public void validarPagamento(Double valorTotalPagar, Fatura fatura) {
		if (valorTotalPagar >= fatura.getValorFatura()) {
			fatura.setStatus(StatusPagamento.PAGA);
		} else {
			fatura.setStatus(StatusPagamento.PENDENTE);
		}
	}
	
	/**
	 * Validador de data de expiração do pagamento da fatura para cada tipo de pagamento.
	 * Se o tipo de pagamento for CARTÃO DE CRÉDITO, caPENDENTEso a data da conta seja de pelo menos 15 dias anteriores a 
	 * data da compra, então o valor do pagamento é incluido (retorna true), caso contrário não é incluido (retorna false).
	 * 
	 * Além disso, checamos se a data de pagamento da conta ultrapassou (true) ou não (false) a data de pagamento da fatura. 
	 * 
	 * @param pagamento - Pagamento da conta. 
	 * @param dataFatura - Vencimento da fatura. 
	 * @return - Retorna um valor booleano, se o pagamento é válido (true) ou não (false). 
	 */
	public boolean pagamentoValido(Pagamento pagamento, Date vencimentoFatura) {
		if (pagamento.getTipoPagamento().equals(TipoPagamento.CARTAO_CREDITO)) {
			Long diasEmSegundos = Math.abs(vencimentoFatura.getTime() - pagamento.getDataPagamento().getTime());
			Long dias = diasEmSegundos / (1000 * 60 * 60 * 24);
			
			return dias >= 15;
		} else {
			return !pagamento.getDataPagamento().after(vencimentoFatura);
		}
	}
	
	/**
	 * Processa o pagamento de uma conta. 
	 * Se o tipo de pagamento da conta é via BOLETO e a data de pagamento da conta (dia atual, hoje) é posterior a 
	 * data de vencimento da conta, então é acrescido 10% no valor da conta. 
	 * 
	 * @param conta - Conta a ser paga. 
	 * @param dataPagamento - Data de pagamento. 
	 * @return - Retorna o pagamento realizado para o boleto. 
	 */
	public Pagamento realizarPagamento(Conta conta, Date dataPagamento) {		
		Double valorPago = conta.getValorPago();
		
		if (conta.getTipoPagamento().equals(TipoPagamento.BOLETO)) {
			if (valorPago < 0.01 || valorPago > 5000.00) {
				throw new IllegalArgumentException("Valor de boleto inválido. Deve ser entre R$0,01 e R$5.000,00.");
			}
			
			if (dataPagamento.after(conta.getData())) {
				valorPago *= 1.1;
			}
		}
		
		return new Pagamento(valorPago, new Date(), conta.getTipoPagamento());
	}
}