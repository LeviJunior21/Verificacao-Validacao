package atividade3;

import org.junit.jupiter.api.Test;

import main.Conta;
import main.Fatura;
import main.ProcessadorContas;
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
        ProcessadorContas processador = new ProcessadorContas();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            processador.processarContas(fatura, Arrays.asList(conta));
        });
    }

}