package br.edu.ufcg.ccc.vv.services;

import br.edu.ufcg.ccc.vv.models.IngressoModel;
import br.edu.ufcg.ccc.vv.models.LoteModel;
import br.edu.ufcg.ccc.vv.models.ShowModel;
import br.edu.ufcg.ccc.vv.models.TipoIngressoEnum;
import br.edu.ufcg.ccc.vv.repository.ShowRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowServices {
    private final ShowRepository showRepository;
    private Long currentIngressoId;
    private Long loteId;

    public ShowServices(ShowRepository showRepository) {
        this.showRepository = showRepository;
        this.currentIngressoId = 0L;
        this.loteId = 0L;
    }

    public void criarShow(Date date, String artista, Double cache, Double totalDespesas, Integer quantLotes, Integer quantIngressosPorLote,Double precoNormal, Boolean isDataEspecial, Double descontoLote, Double porVip){
        throw new RuntimeException();
    }
}
