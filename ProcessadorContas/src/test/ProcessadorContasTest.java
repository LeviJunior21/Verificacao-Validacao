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

		this.fatura = new Fatura(new Date(24, 07, 2024), 100.00, "Usuário 1");
	}
	
	@Test
	void quandoProcessamosContasDeUmaFaturaPaga() {
		// Arrange
		// Nenhuma necessidade além do setUp.
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertEquals("PAGA", this.fatura.getStatus());
	}
	
	@Test
	void quandoProcessamentoContasDeUmaFataturaPendente() {
		// Arrange
		this.fatura = new Fatura(new Date(24, 07, 2024), 500.00, "Usuário 2");
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertEquals("PENDENTE", this.fatura.getStatus());
	}
	
	@Test
	void quandoProcessamosContaAntesDe15Dias() {
		// Arrange
		this.contas = new ArrayList<>();
		this.contas.add(new Conta("001", new Date(06, 06, 2024), 100.00, "CARTAO_CREDITO"));
		
		// Act
		this.processadorContas.processarContas(fatura, contas);
		
		// Assert
		assertEquals("PAGA", fatura.getStatus());
	}
}
