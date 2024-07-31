package main;

import java.util.Date;
import java.util.List;

public class ProcessadorContas {
	
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
		
		validarPagamento(valorTotalPagar, fatura);
	}
	
	private void validarPagamento(Double valorTotalPagar, Fatura fatura) {
		if (valorTotalPagar >= fatura.getValorFatura()) {
			fatura.setStatus("PAGA");
		} else {
			fatura.setStatus("PENDENTE");
		}
	}
	
	private boolean pagamentoValido(Pagamento pagamento, Date dataFatura) {
		if (pagamento.getTipoPagamento().equals("CARTAO CREDITO")) {
			Long diasEmSegundos = Math.abs(dataFatura.getTime() - pagamento.getDataPagamento().getTime());
			Long dias = diasEmSegundos / (1000 * 60 * 60 * 24);
			return dias >= 15;
			
		} else {
			return !pagamento.getDataPagamento().after(dataFatura);
		}
	}
	
	private Pagamento realizarPagamento(Conta conta, Date dataPagamento) {		
		Double valorPago = conta.getValorPago();
		
		if (conta.getTipoPagamento().equals("BOLETO")) {
			if (dataPagamento.after(conta.getData())) {
				valorPago *= 0.1;
			}
		}
		
		return new Pagamento(valorPago, new Date(), conta.getTipoPagamento());
	}
}
