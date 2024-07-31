package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.IngressoModel;
import br.edu.ufcg.ccc.vv.models.LoteModel;
import br.edu.ufcg.ccc.vv.models.TipoIngressoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LoteRepositoryImplTest {
    private LoteRepository loteRepository;

    @BeforeEach
    public void setUp() {
        loteRepository = new LoteRepositoryImpl();
    }

    @Test
    public void testSave() {
        LoteModel lote = new LoteModel();
        lote.setId(1L);
        lote.setIngressos(new ArrayList<>());
        lote.setDesconto(10.0);

        LoteModel savedLote = loteRepository.save(lote);

        assertEquals(lote, savedLote);
        assertTrue(loteRepository.findById(1L).isPresent());
    }

    @Test
    public void testFindById() {
        LoteModel lote = new LoteModel();
        lote.setId(1L);
        lote.setIngressos(new ArrayList<>());
        lote.setDesconto(10.0);
        loteRepository.save(lote);

        Optional<LoteModel> foundLote = loteRepository.findById(1L);

        assertTrue(foundLote.isPresent());
        assertEquals(lote, foundLote.get());
    }

    @Test
    public void testFindAll() {
        LoteModel lote1 = new LoteModel();
        lote1.setId(1L);
        lote1.setIngressos(new ArrayList<>());
        lote1.setDesconto(10.0);

        LoteModel lote2 = new LoteModel();
        lote2.setId(2L);
        lote2.setIngressos(new ArrayList<>());
        lote2.setDesconto(15.0);

        loteRepository.save(lote1);
        loteRepository.save(lote2);

        List<LoteModel> lotes = loteRepository.findAll();

        assertEquals(2, lotes.size());
        assertTrue(lotes.contains(lote1));
        assertTrue(lotes.contains(lote2));
    }

    @Test
    public void testDeleteById() {
        LoteModel lote = new LoteModel();
        lote.setId(1L);
        lote.setIngressos(new ArrayList<>());
        lote.setDesconto(10.0);
        loteRepository.save(lote);

        loteRepository.deleteById(1L);

        assertFalse(loteRepository.findById(1L).isPresent());
    }
}