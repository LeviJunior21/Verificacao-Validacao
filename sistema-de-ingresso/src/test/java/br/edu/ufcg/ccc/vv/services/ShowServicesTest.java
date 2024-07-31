package br.edu.ufcg.ccc.vv.services;

import br.edu.ufcg.ccc.vv.models.*;
import br.edu.ufcg.ccc.vv.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ShowServicesTest {
    private ShowRepository showRepository;
    private ShowServices showServices;

    @BeforeEach
    public void setUp() {
        // Instanciando o repositório em memória
        showRepository = new InMemoryShowRepository();
        showServices = new ShowServices(showRepository);
    }

    @Test
    public void testCriarShowComDescontoZero() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 100;
        Double precoNormal = 10.0;
        Boolean isDataEspecial = false;
        Double descontoLote = 0.00;
        Double vip = 0.30;

        // Execute o método criarShow
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote, vip);

        // Verificar se o show foi salvo corretamente
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();
        assertNotNull(show);

        List<LoteModel> lotes = show.getLotes();
        assertEquals(quantLotes, lotes.size());
        LoteModel lote = lotes.get(0);
        assertEquals(quantIngressosPorLote, lote.getIngressos().size());

        // Verificar o desconto
        assertEquals(descontoLote, lote.getDesconto());

        // Verificar a distribuição dos ingressos
        int totalIngressos = lote.getIngressos().size();
        int expectedVip = (int) (totalIngressos * vip);
        int expectedMeiaEntrada = (int) (totalIngressos * 0.10);
        int expectedNormal = totalIngressos - expectedVip - expectedMeiaEntrada;

        long countVip = lote.getIngressos().stream().filter(i -> i.getTipoIngresso() == TipoIngressoEnum.VIP).count();
        long countMeiaEntrada = lote.getIngressos().stream().filter(i -> i.getTipoIngresso() == TipoIngressoEnum.MEIA_ENTRADA).count();
        long countNormal = lote.getIngressos().stream().filter(i -> i.getTipoIngresso() == TipoIngressoEnum.NORMAL).count();

        assertEquals(expectedVip, countVip);
        assertEquals(expectedMeiaEntrada, countMeiaEntrada);
        assertEquals(expectedNormal, countNormal);
    }

    @Test
    public void testCriarShowComDescontoAcimaDoPermitido() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 100;
        Double precoNormal = 10.0;
        Boolean isDataEspecial = false;
        Double descontoLote = 30.; // Desconto acima do máximo permitido
        Double vip = 0.30;

        // Execute o método criarShow
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote, vip);

        // Verificar se o show foi salvo corretamente
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();
        assertNotNull(show);

        List<LoteModel> lotes = show.getLotes();
        assertEquals(quantLotes, lotes.size());
        LoteModel lote = lotes.get(0);
        assertEquals(quantIngressosPorLote, lote.getIngressos().size());

        // O desconto não deve ultrapassar 25%
        assertEquals(25, lote.getDesconto());
    }

    @Test
    public void testCriarShowSemIngressos() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 0; // Nenhum ingresso
        Double precoNormal = 10.0;
        Boolean isDataEspecial = false;
        Double descontoLote = 10.;
        Double vip = 0.30;

        // Execute o método criarShow
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote, vip);

        // Verificar se o show foi salvo corretamente
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();
        assertNotNull(show);

        List<LoteModel> lotes = show.getLotes();
        assertEquals(quantLotes, lotes.size());
        LoteModel lote = lotes.get(0);
        assertEquals(quantIngressosPorLote, lote.getIngressos().size());
    }

    @Test
    public void testCriarShowComDataEspecial() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 100;
        Double precoNormal = 10.0;
        Boolean isDataEspecial = true; // Data especial
        Double descontoLote = 10.;
        Double vip = 0.30;

        // Execute o método criarShow
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote, vip);

        // Verificar se o show foi salvo corretamente
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();
        assertNotNull(show);
        assertTrue(show.getDespesasInfra() > 2000.0); // Verifica se as despesas foram ajustadas para data especial
        assertEquals(2300.0, show.getDespesasInfra());
    }

    @Test
    public void testCriarShowComDescontoNegativo() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 100;
        Double precoNormal = 10.0;
        Boolean isDataEspecial = false;
        Double descontoLote = -5.; // Desconto negativo
        Double vip = 0.30;

        // Execute o método criarShow
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote, vip);

        // Verificar se o show foi salvo corretamente
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();
        assertNotNull(show);

        List<LoteModel> lotes = show.getLotes();
        assertEquals(quantLotes, lotes.size());
        LoteModel lote = lotes.get(0);
        assertEquals(0, lote.getDesconto());
    }

    @Test
    public void testComprarIngressoComLoteDisponivel() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 10;
        Double precoNormal = 10.0;
        Boolean isDataEspecial = false;
        Integer descontoLote = 0;

        // Cria o show
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote);

        // Recupera o show salvo
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();
        LoteModel lote = show.getLotes().get(0);
        IngressoModel ingresso = lote.getIngressos().get(0);

        // Compra um ingresso
        IngressoModel comprado = showServices.comprarIngresso(data, artista, lote.getId());

        assertNotNull(comprado);
        assertEquals(ingresso, comprado);
        assertTrue(lote.getIngressos().isEmpty()); // Verifica que o ingresso foi removido
    }

    @Test
    public void testComprarIngressoComLoteNaoDisponivel() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 0; // Nenhum ingresso
        Double precoNormal = 10.0;
        Boolean isDataEspecial = false;
        Integer descontoLote = 0;

        // Cria o show
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote);

        // Recupera o show salvo
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();
        LoteModel lote = show.getLotes().get(0);

        // Tenta comprar um ingresso
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            showServices.comprarIngresso(data, artista, lote.getId());
        });

        assertEquals("Nenhum ingresso disponível para o lote", exception.getMessage());
    }

    @Test
    public void testComprarIngressoComLoteInexistente() {
        Date data = new Date();
        String artista = "Artista Teste";
        Double cache = 1000.0;
        Double totalDespesas = 2000.0;
        Integer quantLotes = 1;
        Integer quantIngressosPorLote = 10;
        Double precoNormal = 10.0;
        Boolean isDataEspecial = false;
        Integer descontoLote = 0;

        // Cria o show
        showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote);

        // Recupera o show salvo
        ShowModel show = ((InMemoryShowRepository) showRepository).getSavedShow();

        // Tenta comprar um ingresso de um lote inexistente
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            showServices.comprarIngresso(data, artista, 999L); // ID de lote inexistente
        });

        assertEquals("Lote não encontrado", exception.getMessage());
    }
}

class InMemoryShowRepository implements ShowRepository {
    private ShowModel savedShow;

    @Override
    public ShowModel save(ShowModel show) {
        this.savedShow = show;
        return show;
    }

    @Override
    public Optional<ShowModel> findById(Date id, String artista) {
        return Optional.empty();
    }

    @Override
    public List<ShowModel> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Date id, String artista) {

    }

    public ShowModel getSavedShow() {
        return savedShow;
    }
}
