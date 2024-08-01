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
    
    @Test
    public void testPagamentoValidoCartaoCreditoInvalido() {
        ProcessadorContas processador = new ProcessadorContas();
        
        // Pagamento com cartão de crédito 15 dias antes do vencimento da fatura
        Date dataPagamento = new Date(System.currentTimeMillis() - 15L * 24 * 60 * 60 * 1000); // 15 dias antes
        Date vencimentoFatura = new Date();
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.CARTAO_CREDITO);
        
        boolean resultado = processador.pagamentoValido(pagamento, vencimentoFatura);
        
        assertFalse(resultado, "Pagamento com cartão de crédito deve ser inválido.");
    }

    @Test
    public void testPagamentoValidoBoletoValido() {
        ProcessadorContas processador = new ProcessadorContas();
        
        // Pagamento com boleto na data de vencimento
        Date dataPagamento = new Date();
        Date vencimentoFatura = new Date(System.currentTimeMillis() + 1L * 24 * 60 * 60 * 1000); // 1 dia depois
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.BOLETO);
        
        boolean resultado = processador.pagamentoValido(pagamento, vencimentoFatura);
        
        assertTrue(resultado, "Pagamento com boleto deve ser válido.");
    }
    
    @Test
    public void testPagamentoValidoBoletoInvalido() {
        ProcessadorContas processador = new ProcessadorContas();
        
        // Pagamento com boleto após a data de vencimento
        Date dataPagamento = new Date(System.currentTimeMillis() + 1L * 24 * 60 * 60 * 1000); // 1 dia depois
        Date vencimentoFatura = new Date();
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.BOLETO);
        
        boolean resultado = processador.pagamentoValido(pagamento, vencimentoFatura);
        
        assertFalse(resultado, "Pagamento com boleto deve ser inválido.");
    }
}