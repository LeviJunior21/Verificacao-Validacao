package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.RelatorioModel;

import java.util.List;
import java.util.Optional;

public interface RelatorioRepository {
    RelatorioModel save(RelatorioModel relatorio);
    Optional<RelatorioModel> findById(Integer id);
    List<RelatorioModel> findAll();
    void deleteById(Integer id);
}
