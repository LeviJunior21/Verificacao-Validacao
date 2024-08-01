package br.edu.ufcg.ccc.vv.services;

import br.edu.ufcg.ccc.vv.models.*;
import br.edu.ufcg.ccc.vv.repository.ShowRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Serviço que gerencia operações relacionadas a shows.
 * Inclui a criação de shows, compra de ingressos e geração de relatórios sobre shows.
 */
public class ShowServices {
    private final ShowRepository showRepository;
    private Long currentIngressoId;
    private Long loteId;

    /**
     * Constrói uma instância de ShowServices com o repositório de shows fornecido.
     *
     * @param showRepository o repositório de shows
     */
    public ShowServices(ShowRepository showRepository) {
        this.showRepository = showRepository;
        this.currentIngressoId = 0L;
        this.loteId = 0L;
    }

    /**
     * Cria um show e o salva no repositório.
     *
     * @param date a data do show
     * @param artista o artista do show
     * @param cache o cachê do artista
     * @param totalDespesas as despesas totais do show
     * @param quantLotes a quantidade de lotes de ingressos
     * @param quantIngressosPorLote a quantidade de ingressos por lote
     * @param precoNormal o preço do ingresso normal
     * @param isDataEspecial indica se a data do show é especial
     * @param descontoLote o desconto aplicável a cada lote
     * @param vip a porcentagem de ingressos VIP
     */
    public void criarShow(Date date, String artista, Double cache, Double totalDespesas, Integer quantLotes, Integer quantIngressosPorLote, Double precoNormal, Boolean isDataEspecial, Double descontoLote, Double vip) {
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
            if (descontoLote < 0)
                descontoLote = 0.0;
            LoteModel loteModel = new LoteModel(descontoLote, ingressoModels, loteId++);
            loteModels.add(loteModel);
        }
        if (isDataEspecial)
            totalDespesas = totalDespesas * 1.15;
        ShowModel showModel = new ShowModel(date, artista, cache, totalDespesas, loteModels, isDataEspecial);
        showRepository.save(showModel);
    }

    /**
     * Compra um ingresso para um show específico.
     *
     * @param date a data do show
     * @param artista o artista do show
     * @param idLote o ID do lote de ingressos
     * @param tipo o tipo de ingresso a ser comprado
     * @return o ingresso comprado
     * @throws IllegalStateException se não houver ingressos disponíveis ou se o ingresso solicitado não estiver disponível
     */
    public IngressoModel comprarIngresso(Date date, String artista, Long idLote, TipoIngressoEnum tipo) {
        ShowModel showModel = showRepository.findById(date, artista).orElseThrow(() -> new IllegalArgumentException("Show não encontrado"));

        LoteModel lote = showModel.getLotes().stream()
                .filter(l -> l.getId().equals(idLote))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lote não encontrado"));

        if (lote.getIngressos().isEmpty()) {
            throw new IllegalStateException("Nenhum ingresso disponível para o lote");
        }

        for (IngressoModel ingressoModel : lote.getIngressos()) {
            if (!ingressoModel.isVendido() && (tipo == null || ingressoModel.getTipoIngresso().equals(tipo))) {
                ingressoModel.setVendido(true);
                return ingressoModel;
            }
        }

        throw new IllegalStateException("Nenhum ingresso disponível para o lote");
    }

    /**
     * Cria um relatório sobre um show específico.
     *
     * @param date a data do show
     * @param artista o artista do show
     * @return o relatório criado
     * @throws IllegalArgumentException se o show não for encontrado
     */
    public RelatorioModel criarRelatorio(Date date, String artista) {
        ShowModel showModel = showRepository.findById(date, artista).orElseThrow(() -> new IllegalArgumentException("Show não encontrado"));

        RelatorioModel relatorio = new RelatorioModel();
        Double valorTotal = 0.00;
        int totalIngressosVendidoNormal = 0;
        int totalIngressosVendidoMeia = 0;
        int totalIngressosVendidoVip = 0;
        for (LoteModel loteModel : showModel.getLotes()) {
            for (IngressoModel ingressoModel : loteModel.getIngressos()) {
                if (ingressoModel.isVendido()) {
                    switch (ingressoModel.getTipoIngresso()) {
                        case VIP:
                            totalIngressosVendidoVip++;
                            break;
                        case MEIA_ENTRADA:
                            totalIngressosVendidoMeia++;
                            break;
                        case NORMAL:
                            totalIngressosVendidoNormal++;
                            break;
                    }
                    valorTotal += ingressoModel.getValor();
                }
            }
        }

        relatorio.setNumIngressoMeia(totalIngressosVendidoMeia);
        relatorio.setNumIngressoNormal(totalIngressosVendidoNormal);
        relatorio.setNumIngressoVip(totalIngressosVendidoVip);
        relatorio.setValorTotal(valorTotal);
        if (valorTotal < showModel.getDespesasInfra() + showModel.getCache())
            relatorio.setStatus(StatusEnum.PREJUÍZO);
        else if (valorTotal > showModel.getDespesasInfra() + showModel.getCache()) {
            relatorio.setStatus(StatusEnum.LUCRO);
        } else {
            relatorio.setStatus(StatusEnum.ESTÁVEL);
        }

        return relatorio;
    }
}
