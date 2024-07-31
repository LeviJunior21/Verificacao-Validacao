package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.RelatorioModel;

import java.util.List;
import java.util.Optional;

public class RelatorioRepositoryImpl implements RelatorioRepository {
    @Override
    public RelatorioModel save(RelatorioModel relatorio) {
        return null;
    }

    @Override
    public Optional<RelatorioModel> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<RelatorioModel> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Integer id) {

    }
}
