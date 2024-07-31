package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.IngressoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class IngressoRepositoryImpl implements IngressoRepository {
    private final HashMap<Long, IngressoModel> database = new HashMap<>();

    @Override
    public IngressoModel save(IngressoModel ingresso) {
        database.put(ingresso.getId(), ingresso);
        return ingresso;
    }

    @Override
    public Optional<IngressoModel> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<IngressoModel> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }
}
