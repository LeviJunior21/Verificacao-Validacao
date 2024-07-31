package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.LoteModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LoteRepositoryImpl implements LoteRepository{
    private final HashMap<Long, LoteModel> database = new HashMap<>();

    @Override
    public LoteModel save(LoteModel lote) {
        database.put(lote.getId(), lote);
        return lote;
    }

    @Override
    public Optional<LoteModel> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<LoteModel> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }
}
