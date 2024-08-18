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
    
    @Test
    public void testValorBoletoIntermediario() {
        // Arrange
        Conta conta = new Conta("126", new Date(), 1023.45, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 1023.45, "Ana");
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
    }
    
    @Test
    public void testValorBoletoLimiteSuperior() {
        // Arrange
        Conta conta = new Conta("127", new Date(), 4999.99, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 4999.99, "Carlos");
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
    }
    
    @Test
    public void testValorBoletoLimiteSuperior0() {
        // Arrange
        Conta conta = new Conta("128", new Date(), 5000.00, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 5000.00, "Lucia");
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
    }
    
    @Test
    public void testValorBoletoLimiteSuperio1() {
        // Arrange
        Conta conta = new Conta("129", new Date(), 5000.01, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 5000.00, "Bruno");
        
        // Act
        ProcessadorContas processador = new ProcessadorContas();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            processador.processarContas(fatura, Arrays.asList(conta));
        });
    }
    
    @Test
    public void testValorBoletoLimiteSuperio2() {
        // Arrange
        Conta conta = new Conta("129", new Date(), 5000.02, TipoPagamento.BOLETO);
        Fatura fatura = new Fatura(new Date(), 5000.00, "Bruno");
        
        // Act
        ProcessadorContas processador = new ProcessadorContas();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            processador.processarContas(fatura, Arrays.asList(conta));
        });
    }
}