package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Conta;
import main.Fatura;
import main.ProcessadorContas;
import main.StatusPagamento;
import main.TipoPagamento;

class ProcessadorContasTest {
	private ProcessadorContas processadorContas;
	private List<Conta> contas;
	private Fatura fatura;
	
	@BeforeEach
	void setUp() {
		this.processadorContas = new ProcessadorContas();
		this.contas = new ArrayList<>();
		this.contas.add(new Conta("001", new Date(2024, 07, 24), 100.00, TipoPagamento.CARTAO_CREDITO));

		this.fatura = new Fatura(new Date(2024, 07, 24), 100.00, "Usuário 1");
	}
	
	@Test
	void quandoProcessamosContasDeUmaFaturaPaga() {
		// Arrange
		// Nenhuma necessidade além do setUp.
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertEquals(StatusPagamento.PAGA, this.fatura.getStatusPagamento());
	}
	
	@Test
	void quandoProcessamentoContasDeUmaFataturaPendente() {
		// Arrange
		this.fatura = new Fatura(new Date(24, 07, 2024), 500.00, "Usuário 2");
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertEquals(StatusPagamento.PENDENTE, this.fatura.getStatusPagamento());
	}
	
	@Test
	void quandoProcessamosContaAntesDe15Dias() {
		// Arrange
		this.contas = new ArrayList<>();
		this.contas.add(new Conta("001", new Date(06, 06, 2024), 100.00, TipoPagamento.CARTAO_CREDITO));
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
	}
	
	@Test
	void quandoPagamosUmaContaDepoisDoPrazoComoBoleto() {
		// Arrange
		this.contas = new ArrayList<>();
		Conta conta = new Conta("001", new Date(2024, 06, 10), 100.00, TipoPagamento.BOLETO);
		this.contas.add(conta);
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertAll(
			() -> assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento()),
			() -> assertEquals(100.00, conta.getValorPago())
		);
	}
	
	@Test
	void quandoPagamosUmaContaComFaturaInvalida() {
		// Arrange
		this.contas = new ArrayList<>();
		Conta conta = new Conta("001", new Date(06, 06, 2024), 100.00, TipoPagamento.BOLETO);
		this.contas.add(conta);
		this.fatura = new Fatura(new Date(), -100.00, "Usuário 1");
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertAll(
			() -> assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento())
		);
	}
	
	@Test
	void quandoPagamosUmBoletoJaVencido() {
		// Arrange
		this.contas = new ArrayList<>();
		Conta conta = new Conta("001", new Date(this.fatura.getDataVencimento().getTime() - (1000 * 60 * 60 * 24 * 20)), 100.00, TipoPagamento.BOLETO);
		this.contas.add(conta);
		this.fatura = new Fatura(new Date(), -100.00, "Usuário 1");
				
		// Act
		this.processadorContas.processarContas(fatura, contas);
				
		// Assert
		assertAll(
			() -> assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento())
		);	
	}
	
	@Test
    void quandoPagamentoValidoComBoletoEAtraso() {
		// Arrange
        Date dataPagamento = new Date(this.fatura.getDataVencimento().getTime() - (1000 * 60 * 60 * 24 * 20));
        
        Conta contaBoleto = new Conta("002", dataPagamento, 100.00, TipoPagamento.BOLETO);
        this.contas.add(contaBoleto);

        // Act
        this.processadorContas.processarContas(fatura, contas);
        
        // Assert
        assertEquals(StatusPagamento.PAGA, this.fatura.getStatusPagamento());
    }
	
	@Test
	void quandoRealizamosUmPagamentoDeUmaContaForaDoPrazpPagaComTransferenciaBancaria() {
		// Arrange
		Conta contaBoleto = new Conta("002", new Date(this.fatura.getDataVencimento().getTime() - (1000 * 60 * 60 * 24 * 20)), 100.00, TipoPagamento.BOLETO);
        this.contas.add(contaBoleto);
        
        // Act
        this.processadorContas.processarContas(fatura, contas);

        // Assert
        assertEquals(StatusPagamento.PAGA, this.fatura.getStatusPagamento());
	}
}