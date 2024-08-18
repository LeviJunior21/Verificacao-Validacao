package atividade3;

import org.junit.jupiter.api.Test;

import main.Conta;
import main.Pagamento;
import main.ProcessadorContas;
import main.TipoPagamento;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.Calendar;

public class ProcessarAcrescimoAposVencimentoBoletoTest {

    private Date criarData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.YEAR, ano);
        return calendar.getTime();
    }

    @Test
    public void testPagamentoPosteriorADataComAcrescimo() {
        // Arrange
        Date dataCompra = criarData(23, 07, 2024);
        Date dataPagamento = criarData(24, 07, 2024);
        Conta conta = new Conta("123", dataCompra, 100.00, TipoPagamento.BOLETO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        Pagamento pagamento = processador.realizarPagamento(conta, dataPagamento);

        // Assert
        assertNotNull(pagamento);
        assertEquals(110.00, pagamento.getValorPago(), 0.01);
    }
    

    @Test
    public void testPagamentoNaDataSemAcrescimo() {
        // Arrange
        Date dataCompra = criarData(23, 07, 2024);
        Date dataPagamento = criarData(23, 07, 2024); // Na data da compra
        Conta conta = new Conta("124", dataCompra, 100.00, TipoPagamento.BOLETO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        Pagamento pagamento = processador.realizarPagamento(conta, dataPagamento);

        // Assert
        assertNotNull(pagamento);
        assertEquals(100.00, pagamento.getValorPago());
    }
}