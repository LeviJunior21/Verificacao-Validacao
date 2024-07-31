package br.edu.ufcg.ccc.vv.models;

public class RelatorioModel {
    private Integer numIngresso;
    private Double valorTotal;
    private StatusEnum status;

    public RelatorioModel() {
    }

    public Integer getNumIngresso() {
        return numIngresso;
    }

    public void setNumIngresso(Integer numIngresso) {
        this.numIngresso = numIngresso;
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
