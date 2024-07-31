package br.edu.ufcg.ccc.vv.models;

public class IngressoModel {
    private Long id;
    private TipoIngressoEnum tipoIngresso;
    private Boolean isVendido;
    private Double valor;

    public IngressoModel() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IngressoModel{");
        sb.append("valor=").append(valor);
        sb.append(", isVendido=").append(isVendido);
        sb.append(", tipoIngresso=").append(tipoIngresso);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public IngressoModel(Long id, TipoIngressoEnum tipoIngresso, Boolean isVendido, Double valor) {
        this.id = id;
        this.tipoIngresso = tipoIngresso;
        this.isVendido = isVendido;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoIngressoEnum getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(TipoIngressoEnum tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Boolean isVendido() {
        return isVendido;
    }

    public void setVendido(Boolean vendido) {
        isVendido = vendido;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
