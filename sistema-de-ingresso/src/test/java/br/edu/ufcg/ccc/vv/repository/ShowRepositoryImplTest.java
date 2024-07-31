package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.ShowModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ShowRepositoryImplTest {
    private ShowRepository showRepository;

    @BeforeEach
    public void setUp() {
        showRepository = new ShowRepositoryImpl();
    }

    @Test
    public void testSave() {
        Date data = new Date();
        ShowModel show = new ShowModel(true, 5, 1000.0, 5000.0, "Artista Teste", data);

        ShowModel savedShow = showRepository.save(show);

        assertEquals(show, savedShow);
        assertTrue(showRepository.findById(data, "Artista Teste").isPresent());
    }

    @Test
    public void testFindById() {
        Date data = new Date();
        ShowModel show = new ShowModel(true, 5, 1000.0, 5000.0, "Artista Teste", data);
        showRepository.save(show);

        Optional<ShowModel> foundShow = showRepository.findById(data, "Artista Teste");

        assertTrue(foundShow.isPresent());
        assertEquals(show, foundShow.get());
    }

    @Test
    public void testFindAll() {
        Date data1 = new Date();
        ShowModel show1 = new ShowModel(true, 5, 1000.0, 5000.0, "Artista Teste 1", data1);

        Date data2 = new Date();
        ShowModel show2 = new ShowModel(false, 10, 2000.0, 10000.0, "Artista Teste 2", data2);

        showRepository.save(show1);
        showRepository.save(show2);

        List<ShowModel> shows = showRepository.findAll();

        assertEquals(2, shows.size());
        assertTrue(shows.contains(show1));
        assertTrue(shows.contains(show2));
    }

    @Test
    public void testDeleteById() {
        Date data = new Date();
        ShowModel show = new ShowModel(true, 5, 1000.0, 5000.0, "Artista Teste", data);
        showRepository.save(show);

        showRepository.deleteById(data, "Artista Teste");

        assertFalse(showRepository.findById(data, "Artista Teste").isPresent());
    }
}