package br.edu.ufcg.ccc.vv.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShowModelTest {
    private Date date;
    private String NOME_ARTISTA = "Frei Gilson";
    private Double CACHE = 100.0;
    private Double DESPESAS_INFRA = 12000.00;
    private Integer LOTES = 3;
    private Boolean IS_DATA_ESPECIAL = Boolean.TRUE;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 25);

        date = calendar.getTime();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getData() {
        ShowModel showModel = new ShowModel(IS_DATA_ESPECIAL, LOTES, DESPESAS_INFRA, CACHE, NOME_ARTISTA, date);
        assertEquals(this.date, showModel.getData(), "Data diferente");
    }

    @Test
    void getArtista() {
        ShowModel showModel = new ShowModel(IS_DATA_ESPECIAL, LOTES, DESPESAS_INFRA, CACHE, NOME_ARTISTA, date);
        assertEquals(this.NOME_ARTISTA, showModel.getArtista());
    }

    @Test
    void getCache() {
        ShowModel showModel = new ShowModel(IS_DATA_ESPECIAL, LOTES, DESPESAS_INFRA, CACHE, NOME_ARTISTA, date);
        assertEquals(this.CACHE, showModel.getCache(), "Cache diferente");
    }

    @Test
    void getDespesasInfra() {
        ShowModel showModel = new ShowModel(IS_DATA_ESPECIAL, LOTES, DESPESAS_INFRA, CACHE, NOME_ARTISTA, date);
        assertEquals(this.DESPESAS_INFRA, showModel.getDespesasInfra());
    }

    @Test
    void getLotes() {
        ShowModel showModel = new ShowModel(IS_DATA_ESPECIAL, LOTES, DESPESAS_INFRA, CACHE, NOME_ARTISTA, date);
        assertEquals(this.LOTES, showModel.getLotes(), "Lotes diferente");
    }

    @Test
    void getIsEspecial() {
        ShowModel showModel = new ShowModel(IS_DATA_ESPECIAL, LOTES, DESPESAS_INFRA, CACHE, NOME_ARTISTA, date);
        assertEquals(this.IS_DATA_ESPECIAL, showModel.isEspecial());
    }

    @Test
    void setData() {
        ShowModel showModel = new ShowModel();
        showModel.setData(date);
        assertEquals(this.date, showModel.getData(), "Data diferente");
    }

    @Test
    void setArtista() {
        ShowModel showModel = new ShowModel();
        showModel.setArtista(NOME_ARTISTA);
        assertEquals(this.NOME_ARTISTA, showModel.getArtista());
    }

    @Test
    void setCache() {
        ShowModel showModel = new ShowModel();
        showModel.setCache(CACHE);
        assertEquals(CACHE, showModel.getCache(), "Cache diferente");
    }

    @Test
    void setDespesasInfra() {
        ShowModel showModel = new ShowModel();
        showModel.setDespesasInfra(DESPESAS_INFRA);
        assertEquals(DESPESAS_INFRA, showModel.getDespesasInfra());
    }

    @Test
    void setLotes() {
        ShowModel showModel = new ShowModel();
        showModel.setLotes(LOTES);
        assertEquals(LOTES, showModel.getLotes(), "Lotes diferente");
    }

    @Test
    void setIsEspecial() {
        ShowModel showModel = new ShowModel();
        showModel.setEspecial(IS_DATA_ESPECIAL);
        assertEquals(IS_DATA_ESPECIAL, showModel.isEspecial());
    }

    @Test
    void toStringTest() {
        ShowModel showModel = new ShowModel(IS_DATA_ESPECIAL, LOTES, DESPESAS_INFRA, CACHE, NOME_ARTISTA, date);
        assertEquals(showModel.toString(), "data=" + date +
                ", artista='" + NOME_ARTISTA + '\'' +
                ", cache=" + CACHE +
                ", despesasInfra=" + DESPESAS_INFRA +
                ", lotes=" + LOTES +
                ", isEspecial=" + IS_DATA_ESPECIAL, "Date diferente");
    }
}