package test;

import org.junit.jupiter.api.Test;
import main.Fatura;
import main.ProcessadorContas;
import main.StatusPagamento;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;

public class ValidarVencimentoContaSobreFaturaTest {
    private ProcessadorContas processadorContas;

    @BeforeEach
    void setUp() {
        processadorContas = new ProcessadorContas();
    }

    @Test
    void testValidarPagamentoFaturaPaga() {
    	// Arrange
        Fatura fatura = new Fatura(new Date(), 100.00, "Usuario 1");
        Double valorTotalPagar = 150.00;        
        
        // Act
        processadorContas.validarPagamento(valorTotalPagar, fatura);
        
        // Assert
        assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento(), "A fatura deve ser marcada como PAGA quando o valor total pago é maior ou igual ao valor da fatura");
    }

    @Test
    void testValidarPagamentoFaturaPendente() {
    	// Arrange
        Fatura fatura = new Fatura(new Date(), 100.00, "Usuario 1"); 
        Double valorTotalPagar = 50.00;
        
        // Act
        processadorContas.validarPagamento(valorTotalPagar, fatura);
        
        // Assert
        assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento(), "A fatura deve ser marcada como PENDENTE quando o valor total pago é menor que o valor da fatura");
    }
}
