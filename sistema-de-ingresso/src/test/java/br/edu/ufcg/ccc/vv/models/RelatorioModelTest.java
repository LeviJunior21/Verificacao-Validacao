package br.edu.ufcg.ccc.vv.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RelatorioModelTest {

    private RelatorioModel relatorio;

    @BeforeEach
    public void setUp() {
        relatorio = new RelatorioModel();
    }

    @Test
    public void testConstructor() {
        assertNull(relatorio.getNumIngresso());
        assertNull(relatorio.getValorTotal());
        assertNull(relatorio.getStatus());
    }

    @Test
    public void testGetSetNumIngresso() {
        Integer numIngresso = 5;
        relatorio.setNumIngresso(numIngresso);
        assertEquals(numIngresso, relatorio.getNumIngresso());

        relatorio.setNumIngresso(null);
        assertNull(relatorio.getNumIngresso());

        numIngresso = -1;
        relatorio.setNumIngresso(numIngresso);
        assertEquals(numIngresso, relatorio.getNumIngresso());
    }

    @Test
    public void testGetSetValorTotal() {
        Double valorTotal = 100.0;
        relatorio.setValorTotal(valorTotal);
        assertEquals(valorTotal, relatorio.getValorTotal(), 0.001);

        relatorio.setValorTotal(null);
        assertNull(relatorio.getValorTotal());

        valorTotal = -50.0;
        relatorio.setValorTotal(valorTotal);
        assertEquals(valorTotal, relatorio.getValorTotal(), 0.001);
    }

    @Test
    public void testGetSetStatus() {
        StatusEnum status = StatusEnum.ESTÁVEL;
        relatorio.setStatus(status);
        assertEquals(status, relatorio.getStatus());

        relatorio.setStatus(null);
        assertNull(relatorio.getStatus());

        status = StatusEnum.LUCRO;
        relatorio.setStatus(status);
        assertEquals(status, relatorio.getStatus());
    }
}
