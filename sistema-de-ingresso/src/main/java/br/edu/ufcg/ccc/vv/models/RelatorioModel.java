package br.edu.ufcg.ccc.vv.models;

public class RelatorioModel {
    private Integer numIngressoVip;
    private Integer numIngressoNormal;
    private Integer numIngressoMeia;
    private Double valorTotal;
    private StatusEnum status;

    public RelatorioModel() {
    }


    public Integer getNumIngressoVip() {
        return numIngressoVip;
    }

    public void setNumIngressoVip(Integer numIngressoVip) {
        this.numIngressoVip = numIngressoVip;
    }

    public Integer getNumIngressoNormal() {
        return numIngressoNormal;
    }

    public void setNumIngressoNormal(Integer numIngressoNormal) {
        this.numIngressoNormal = numIngressoNormal;
    }

    public Integer getNumIngressoMeia() {
        return numIngressoMeia;
    }

    public void setNumIngressoMeia(Integer numIngressoMeia) {
        this.numIngressoMeia = numIngressoMeia;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
