package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.RelatorioModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RelatorioRepositoryImpl implements RelatorioRepository {
    private final HashMap<Integer, RelatorioModel> database = new HashMap<>();

    @Override
    public RelatorioModel save(RelatorioModel relatorio) {
        database.put(relatorio.getNumIngresso(), relatorio);
        return relatorio;
    }

    @Override
    public Optional<RelatorioModel> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<RelatorioModel> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Integer id) {
        database.remove(id);
    }
}
