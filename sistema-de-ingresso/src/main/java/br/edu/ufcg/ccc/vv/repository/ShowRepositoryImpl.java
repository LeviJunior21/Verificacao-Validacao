package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.ShowModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ShowRepositoryImpl implements ShowRepository {
    @Override
    public ShowModel save(ShowModel show) {
        return null;
    }

    @Override
    public Optional<ShowModel> findById(Date id, String artista) {
        return Optional.empty();
    }

    @Override
    public List<ShowModel> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Date id, String artista) {

    }
}
