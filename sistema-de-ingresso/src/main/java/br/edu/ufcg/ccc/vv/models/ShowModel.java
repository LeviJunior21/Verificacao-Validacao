package br.edu.ufcg.ccc.vv.models;

import java.util.Date;
import java.util.List;

public class ShowModel {
    private Date data;
    private String artista;
    private Double cache;
    private Double despesasInfra;
    private List<LoteModel> lotes;
    private Boolean isEspecial;

    public ShowModel(Date data, String artista, Double cache, Double despesasInfra, List<LoteModel> lotes, Boolean isEspecial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesasInfra = despesasInfra;
        this.lotes = lotes;
        this.isEspecial = isEspecial;
    }

    public ShowModel() {

    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Double getCache() {
        return cache;
    }

    public void setCache(Double cache) {
        this.cache = cache;
    }

    public Double getDespesasInfra() {
        return despesasInfra;
    }

    public void setDespesasInfra(Double despesasInfra) {
        this.despesasInfra = despesasInfra;
    }

    public List<LoteModel> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteModel> lotes) {
        this.lotes = lotes;
    }

    public Boolean isEspecial() {
        return isEspecial;
    }

    public void setEspecial(Boolean especial) {
        isEspecial = especial;
    }

    @Override
    public String toString() {
        return "data=" + data +
                ", artista='" + artista + '\'' +
                ", cache=" + cache +
                ", despesasInfra=" + despesasInfra +
                ", lotes=" + lotes +
                ", isEspecial=" + isEspecial;
    }
}
