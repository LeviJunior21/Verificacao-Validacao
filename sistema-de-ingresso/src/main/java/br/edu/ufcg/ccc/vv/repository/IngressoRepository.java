package br.edu.ufcg.ccc.vv.repository;

import br.edu.ufcg.ccc.vv.models.IngressoModel;

import java.util.List;
import java.util.Optional;

public interface IngressoRepository {
    IngressoModel save(IngressoModel ingresso);
    Optional<IngressoModel> findById(Long id);
    List<IngressoModel> findAll();
    void deleteById(Long id);
}
