package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.IngressoModel;
import br.edu.ufcg.ccc.vv.models.TipoIngressoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class IngressoRepositoryImplTest {
    private IngressoRepository ingressoRepository;

    @BeforeEach
    public void setUp() {
        ingressoRepository = new IngressoRepositoryImpl();
    }

    @Test
    public void testSave() {
        IngressoModel ingresso = new IngressoModel();
        ingresso.setId(1L);
        ingresso.setTipoIngresso(TipoIngressoEnum.NORMAL);
        ingresso.setVendido(false);
        ingresso.setValor(50.0);

        IngressoModel savedIngresso = ingressoRepository.save(ingresso);

        assertEquals(ingresso, savedIngresso);
        assertTrue(ingressoRepository.findById(1L).isPresent());
    }

    @Test
    public void testFindById() {
        IngressoModel ingresso = new IngressoModel();
        ingresso.setId(1L);
        ingresso.setTipoIngresso(TipoIngressoEnum.VIP);
        ingresso.setVendido(true);
        ingresso.setValor(100.0);
        ingressoRepository.save(ingresso);

        Optional<IngressoModel> foundIngresso = ingressoRepository.findById(1L);

        assertTrue(foundIngresso.isPresent());
        assertEquals(ingresso, foundIngresso.get());
    }

    @Test
    public void testFindAll() {
        IngressoModel ingresso1 = new IngressoModel();
        ingresso1.setId(1L);
        ingresso1.setTipoIngresso(TipoIngressoEnum.NORMAL);
        ingresso1.setVendido(false);
        ingresso1.setValor(50.0);

        IngressoModel ingresso2 = new IngressoModel();
        ingresso2.setId(2L);
        ingresso2.setTipoIngresso(TipoIngressoEnum.VIP);
        ingresso2.setVendido(true);
        ingresso2.setValor(100.0);

        ingressoRepository.save(ingresso1);
        ingressoRepository.save(ingresso2);

        List<IngressoModel> ingressos = ingressoRepository.findAll();

        assertEquals(2, ingressos.size());
        assertTrue(ingressos.contains(ingresso1));
        assertTrue(ingressos.contains(ingresso2));
    }

    @Test
    public void testDeleteById() {
        IngressoModel ingresso = new IngressoModel();
        ingresso.setId(1L);
        ingresso.setTipoIngresso(TipoIngressoEnum.NORMAL);
        ingresso.setVendido(false);
        ingresso.setValor(50.0);
        ingressoRepository.save(ingresso);

        ingressoRepository.deleteById(1L);

        assertFalse(ingressoRepository.findById(1L).isPresent());
    }
}
