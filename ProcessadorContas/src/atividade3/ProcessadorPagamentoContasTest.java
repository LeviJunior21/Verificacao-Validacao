package atividade3;

import org.junit.jupiter.api.Test;

import main.Conta;
import main.Fatura;
import main.ProcessadorContas;
import main.StatusPagamento;
import main.TipoPagamento;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.Arrays;

public class ProcessadorPagamentoContasTest {

    @Test
    public void testValorBoletoLimiteInferior_Zero() {
        // Arrange
        Conta conta = new Conta("123", new Date(), 0.00, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 1000.00, "João");
        
        // Act
        ProcessadorContas processador = new ProcessadorContas();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            processador.processarContas(fatura, Arrays.asList(conta));
        });
    }

    @Test
    public void testValorBoletoLimiteInferior1() {
        // Arrange
        Conta conta = new Conta("124", new Date(), 0.01, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 1000.00, "Maria");
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
    }
    

    @Test
    public void testValorBoletoLimiteInferior2() {
        // Arrange
        Conta conta = new Conta("125", new Date(), 0.02, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 1000.00, "José");
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
    }
}