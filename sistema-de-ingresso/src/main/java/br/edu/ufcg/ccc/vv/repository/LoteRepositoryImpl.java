package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.LoteModel;

import java.util.List;
import java.util.Optional;

public class LoteRepositoryImpl implements LoteRepository{
    @Override
    public LoteModel save(LoteModel lote) {
        return null;
    }

    @Override
    public Optional<LoteModel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<LoteModel> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
