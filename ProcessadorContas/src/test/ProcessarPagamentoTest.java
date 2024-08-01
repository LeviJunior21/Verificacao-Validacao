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
        Date dataVencimento = new Date(dataPagamento.getTime() - (1000 * 60 * 60 * 24 * 1)); // 1 dia atrás
        Conta conta = new Conta("001", dataVencimento, 100.00, TipoPagamento.BOLETO);
        
        Pagamento pagamento = processadorContas.realizarPagamento(conta, dataPagamento);
        
        assertNotNull(pagamento, "Pagamento não deve ser nulo");
        
        // Valor esperado com acréscimo de 10% devido ao pagamento após o vencimento
        Double valorEsperado = 110.00;
        Double valorPago = pagamento.getValorPago();
        
        // Margem de erro para comparação de valores de ponto flutuante
        double margemErro = 0.01;
        
        assertEquals(valorEsperado, valorPago, margemErro, "O valor pago deve ter 10% de acréscimo");
        assertEquals(TipoPagamento.BOLETO, pagamento.getTipoPagamento(), "O tipo de pagamento deve ser BOLETO");
    }

}