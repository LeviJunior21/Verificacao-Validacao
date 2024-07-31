package br.edu.ufcg.ccc.vv.models;

import java.util.List;

public class LoteModel {
    private Long id;
    private List<IngressoModel> ingressos;
    private Double desconto;

    public LoteModel(Double desconto, List<IngressoModel> ingressos, Long id) {
        this.desconto = desconto;
        this.ingressos = ingressos;
        this.id = id;
    }

    public LoteModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IngressoModel> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<IngressoModel> ingressos) {
        this.ingressos = ingressos;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}
