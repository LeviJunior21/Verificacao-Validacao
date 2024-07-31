package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.RelatorioModel;
import br.edu.ufcg.ccc.vv.models.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioRepositoryImplTest {
    private RelatorioRepository relatorioRepository;

    @BeforeEach
    public void setUp() {
        relatorioRepository = new RelatorioRepositoryImpl();
    }

    @Test
    public void testSave() {
        RelatorioModel relatorio = new RelatorioModel();
        relatorio.setNumIngresso(1);
        relatorio.setValorTotal(150.0);
        relatorio.setStatus(StatusEnum.LUCRO);

        RelatorioModel savedRelatorio = relatorioRepository.save(relatorio);

        assertEquals(relatorio, savedRelatorio);
        assertTrue(relatorioRepository.findById(1).isPresent());
    }

    @Test
    public void testFindById() {
        RelatorioModel relatorio = new RelatorioModel();
        relatorio.setNumIngresso(1);
        relatorio.setValorTotal(150.0);
        relatorio.setStatus(StatusEnum.LUCRO);
        relatorioRepository.save(relatorio);

        Optional<RelatorioModel> foundRelatorio = relatorioRepository.findById(1);

        assertTrue(foundRelatorio.isPresent());
        assertEquals(relatorio, foundRelatorio.get());
    }

    @Test
    public void testFindAll() {
        RelatorioModel relatorio1 = new RelatorioModel();
        relatorio1.setNumIngresso(1);
        relatorio1.setValorTotal(150.0);
        relatorio1.setStatus(StatusEnum.LUCRO);

        RelatorioModel relatorio2 = new RelatorioModel();
        relatorio2.setNumIngresso(2);
        relatorio2.setValorTotal(200.0);
        relatorio2.setStatus(StatusEnum.PREJUÍZO);

        relatorioRepository.save(relatorio1);
        relatorioRepository.save(relatorio2);

        List<RelatorioModel> relatorios = relatorioRepository.findAll();

        assertEquals(2, relatorios.size());
        assertTrue(relatorios.contains(relatorio1));
        assertTrue(relatorios.contains(relatorio2));
    }

    @Test
    public void testDeleteById() {
        RelatorioModel relatorio = new RelatorioModel();
        relatorio.setNumIngresso(1);
        relatorio.setValorTotal(150.0);
        relatorio.setStatus(StatusEnum.LUCRO);
        relatorioRepository.save(relatorio);

        relatorioRepository.deleteById(1);

        assertFalse(relatorioRepository.findById(1).isPresent());
    }
}