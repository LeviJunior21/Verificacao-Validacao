package test;

import org.junit.jupiter.api.Test;

import main.Conta;
import main.Pagamento;
import main.ProcessadorContas;
import main.TipoPagamento;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ProcessarPagamentoTest {

    private ProcessadorContas processadorContas;
    private Date dataPagamento;

    @BeforeEach
    void setUp() {
        processadorContas = new ProcessadorContas();
        dataPagamento = new Date();
    }

    @Test
    void testRealizarPagamentoBoletoPagoAposVencimento() {
        Date dataVencimento = new Date(dataPagamento.getTime() - (1000 * 60 * 60 * 24 * 1));
        Conta conta = new Conta("001", dataVencimento, 100.00, TipoPagamento.BOLETO);
        
        Pagamento pagamento = processadorContas.realizarPagamento(conta, dataPagamento);
        
        assertNotNull(pagamento, "Pagamento não deve ser nulo");
        assertEquals(110.00, pagamento.getValorPago(), "O valor pago deve ter 10% de acréscimo");
        assertEquals(TipoPagamento.BOLETO, pagamento.getTipoPagamento(), "O tipo de pagamento deve ser BOLETO");
    }

}