package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Conta;
import main.Fatura;
import main.ProcessadorContas;

class ProcessadorContasTest {
	
	private ProcessadorContas processadorContas;
	private List<Conta> contas;
	private Fatura fatura;
	
	@BeforeEach
	void setUp() {
		this.processadorContas = new ProcessadorContas();
		this.contas = new ArrayList<>();
		this.contas.add(new Conta("001", new Date(23, 07, 2024), 100.00, "CARTAO_CREDITO"));
		this.contas.add(new Conta("002", new Date(23, 07, 2024), 100.00, "BOLETO"));
		this.contas.add(new Conta("003", new Date(23, 07, 2024), 100.00, "TRANSFERENCIA_BANCARIA"));
		
		this.fatura = new Fatura(new Date(24, 07, 2024), 400.00, "Usuário 1");
	}
	
	@Test
	void quandoProcessamosContasDeUmaFaturaPendente() {
		this.processadorContas.processarContas(fatura, contas);
		
		assertEquals(this.fatura.getStatus(), "PENDENTE");
	}

}
