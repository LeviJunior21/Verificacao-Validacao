package test;

import org.junit.jupiter.api.Test;

import main.Pagamento;
import main.ProcessadorContas;
import main.TipoPagamento;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ValidarPagamentoTest {

    @Test
    public void testPagamentoValidoCartaoCreditoValido() {
        ProcessadorContas processador = new ProcessadorContas();
        
        // Pagamento com cartão de crédito 16 dias antes do vencimento da fatura
        Date dataPagamento = new Date(System.currentTimeMillis() - 16L * 24 * 60 * 60 * 1000); // 16 dias antes
        Date vencimentoFatura = new Date();
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.CARTAO_CREDITO);
        
        boolean resultado = processador.pagamentoValido(pagamento, vencimentoFatura);
        
        assertTrue(resultado, "Pagamento com cartão de crédito deve ser válido.");
    }
}