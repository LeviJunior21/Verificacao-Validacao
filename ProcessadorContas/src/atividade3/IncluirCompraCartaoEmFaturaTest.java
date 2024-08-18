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
}