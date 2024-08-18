package atividade3;

import org.junit.jupiter.api.Test;

import main.Conta;
import main.Pagamento;
import main.ProcessadorContas;
import main.TipoPagamento;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;

public class IncluirCompraCartaoEmFaturaTest {
	
    private Date criarData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.YEAR, ano);
        return calendar.getTime();
    }

    @Test
    public void testContaIncluidaNaFatura15DiasAntes() {
        // Arrange
        Date dataFatura = criarData(23, 07, 2024);
        Date dataConta = criarData(06, 07, 2024);
        Conta conta = new Conta("123", dataConta, 500.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        boolean isValida = processador.pagamentoValido(new Pagamento(500.00, dataConta, TipoPagamento.CARTAO_CREDITO), dataFatura);

        // Assert
        assertTrue(isValida, "A conta deveria ser incluída na fatura");
    }
    
    @Test
    public void testContaIncluidaNaFaturaExatamente15DiasAntes() {
        // Arrange
        Date dataFatura = criarData(23, 07, 2024);
        Date dataConta = criarData(07, 07, 2024);
        Conta conta = new Conta("124", dataConta, 500.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        boolean isValida = processador.pagamentoValido(new Pagamento(500.00, dataConta, TipoPagamento.CARTAO_CREDITO), dataFatura);

        // Assert
        assertTrue(isValida, "A conta deveria ser incluída na fatura");
    }

    @Test
    public void testContaIncluidaNaFaturaMenosDe15DiasAntes() {
        // Arrange
        Date dataFatura = criarData(23, 07, 2024);
        Date dataConta = criarData(8, 07, 2024);
        Conta conta = new Conta("125", dataConta, 500.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        boolean isValida = processador.pagamentoValido(new Pagamento(500.00, dataConta, TipoPagamento.CARTAO_CREDITO), dataFatura);

        // Assert
        assertTrue(isValida, "A conta deveria ser incluída na fatura");
    }

    @Test
    public void testContaNaoIncluidaNaFatura14DiasAntes() {
        // Arrange
        Date dataFatura = criarData(23, 07, 2024);
        Date dataConta = criarData(9, 07, 2024);
        Conta conta = new Conta("126", dataConta, 500.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        boolean isValida = processador.pagamentoValido(new Pagamento(500.00, dataConta, TipoPagamento.CARTAO_CREDITO), dataFatura);

        // Assert
        assertFalse(isValida, "A conta não deveria ser incluída na fatura");
    }
    
    @Test
    public void testContaNaoIncluidaNaFatura13DiasAntes() {
        // Arrange
        Date dataFatura = criarData(23, 07, 2024);
        Date dataConta = criarData(10, 07, 2024);
        Conta conta = new Conta("127", dataConta, 500.00, TipoPagamento.CARTAO_CREDITO);
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        boolean isValida = processador.pagamentoValido(new Pagamento(500.00, dataConta, TipoPagamento.CARTAO_CREDITO), dataFatura);

        // Assert
        assertFalse(isValida, "A conta não deveria ser incluída na fatura");
    }
}