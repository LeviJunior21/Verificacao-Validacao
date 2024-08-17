package br.edu.ufcg.ccc.vv.functionalTests;

import br.edu.ufcg.ccc.vv.models.LoteModel;
import br.edu.ufcg.ccc.vv.models.ShowModel;
import br.edu.ufcg.ccc.vv.models.TipoIngressoEnum;
import br.edu.ufcg.ccc.vv.repository.ShowRepository;
import br.edu.ufcg.ccc.vv.services.ShowServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnaliseValorLimitePorcentagemDeIngressos {
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
        double vip = 19;
        try {
            showServices.criarShow(data, artista, cache, totalDespesas, quantLotes, quantIngressosPorLote, precoNormal, isDataEspecial, descontoLote, vip);
            fail("Era esperado que isso não funcionasse");
        }catch (Exception e){
            assertEquals("Limites de VIP estão inválidos", e.getMessage());
        }
    }

}
class InMemoryShowRepository implements ShowRepository {
    private final Map<String, ShowModel> database = new HashMap<>();

    @Override
    public ShowModel save(ShowModel show) {
        String key = generateKey(show.getData(), show.getArtista());
        database.put(key, show);
        return show;
    }

    @Override
    public Optional<ShowModel> findById(Date id, String artista) {
        String key = generateKey(id, artista);
        return Optional.ofNullable(database.get(key));
    }

    @Override
    public List<ShowModel> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Date id, String artista) {

    }

    private String generateKey(Date data, String artista) {
        return data.toString() + "|" + artista;
    }
}
