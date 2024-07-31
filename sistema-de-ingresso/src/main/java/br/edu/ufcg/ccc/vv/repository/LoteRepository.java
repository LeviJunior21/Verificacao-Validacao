package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.LoteModel;

import java.util.List;
import java.util.Optional;

public interface LoteRepository {
    LoteModel save(LoteModel lote);
    Optional<LoteModel> findById(Long id);
    List<LoteModel> findAll();
    void deleteById(Long id);
}
