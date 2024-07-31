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

    public void criarShow(Date date, String artista, Double cache, Double totalDespesas, Integer quantLotes, Integer quantIngressosPorLote,Double precoNormal, Boolean isDataEspecial, Double descontoLote, Double vip){
        List<LoteModel> loteModels = new ArrayList<>();
        for (int i = 0; i < quantLotes; i++) {
            List<IngressoModel> ingressoModels = new ArrayList<>();
            int expectedVip = (int) (quantIngressosPorLote * vip);
            int expectedMeiaEntrada = (int) (quantIngressosPorLote * 0.10);
            int expectedNormal = quantIngressosPorLote - expectedVip - expectedMeiaEntrada;
            for (int j = 0; j < expectedVip; j++) {
                IngressoModel ingressoModel = new IngressoModel(currentIngressoId++, TipoIngressoEnum.VIP, false, precoNormal * 2);
                ingressoModels.add(ingressoModel);
            }
            for (int j = 0; j < expectedMeiaEntrada; j++) {
                IngressoModel ingressoModel = new IngressoModel(currentIngressoId++, TipoIngressoEnum.MEIA_ENTRADA, false, precoNormal * 0.5);
                ingressoModels.add(ingressoModel);
            }
            for (int j = 0; j < expectedNormal; j++) {
                IngressoModel ingressoModel = new IngressoModel(currentIngressoId++, TipoIngressoEnum.NORMAL, false, precoNormal);
                ingressoModels.add(ingressoModel);
            }
            if (descontoLote > 25)
                descontoLote = 25.0;
            if(descontoLote < 0)
                descontoLote = 0.0;
            LoteModel loteModel = new LoteModel(descontoLote, ingressoModels, loteId++);
            loteModels.add(loteModel);
        }
        if(isDataEspecial)
            totalDespesas = totalDespesas * 1.15;
        ShowModel showModel = new ShowModel(date, artista, cache, totalDespesas,loteModels,isDataEspecial);
        showRepository.save(showModel);
    }
}
