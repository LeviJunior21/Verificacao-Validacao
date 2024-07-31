package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.IngressoModel;

import java.util.List;
import java.util.Optional;

public class IngressoRepositoryImpl implements IngressoRepository {
    @Override
    public IngressoModel save(IngressoModel ingresso) {
        return null;
    }

    @Override
    public Optional<IngressoModel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<IngressoModel> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
