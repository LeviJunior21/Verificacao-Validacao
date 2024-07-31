package br.edu.ufcg.ccc.vv.models;

import java.util.Date;

public class ShowModel {
    private Date data;
    private String artista;
    private Double cache;
    private Double despesasInfra;
    private Integer lotes;
    private Boolean isEspecial;

    public ShowModel(Boolean isEspecial, Integer lotes, Double despesasInfra, Double cache, String artista, Date data) {
        this.isEspecial = isEspecial;
        this.lotes = lotes;
        this.despesasInfra = despesasInfra;
        this.cache = cache;
        this.artista = artista;
        this.data = data;
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

    public Integer getLotes() {
        return lotes;
    }

    public void setLotes(Integer lotes) {
        this.lotes = lotes;
    }

    public Boolean isEspecial() {
        return isEspecial;
    }

    public void setEspecial(Boolean especial) {
        isEspecial = especial;
    }
}
