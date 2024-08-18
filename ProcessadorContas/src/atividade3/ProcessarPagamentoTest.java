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
import java.util.Calendar;

public class ProcessarPagamentoTest {

    private Date criarData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.YEAR, ano);
        return calendar.getTime();
    }

    @Test
    public void testValorFaturaMenorQueSomaPagamentos() {
        // Arrange
        Fatura fatura = new Fatura(criarData(23, 07, 2024), 999.98, "Usuario1");
        Conta conta = new Conta("123", criarData(01, 07, 2024), 1000.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
    }
    

    @Test
    public void testValorFaturaIgualSomaPagamentos() {
        // Arrange
        Fatura fatura = new Fatura(criarData(23, 07, 2024), 1000.00, "Usuario2");
        Conta conta = new Conta("124", criarData(01, 07, 2024), 1000.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
    }
    

    @Test
    public void testValorFaturaMaiorQueSomaPagamentosPorUmCentavo() {
        // Arrange
        Fatura fatura = new Fatura(criarData(23, 07, 2024), 1000.01, "Usuario3");
        Conta conta = new Conta("125", criarData(01, 07, 2024), 1000.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
    }
    

    @Test
    public void testValorFaturaMaiorQueSomaPagamentosPorDoisCentavos() {
        // Arrange
        Fatura fatura = new Fatura(criarData(23, 07, 2024), 1000.02, "Usuario4");
        Conta conta = new Conta("126", criarData(01, 07, 2024), 1000.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        processador.processarContas(fatura, Arrays.asList(conta));

        // Assert
        assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
    }
}