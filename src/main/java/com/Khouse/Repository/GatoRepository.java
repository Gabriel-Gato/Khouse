package com.Khouse.Repository;

import com.Khouse.Model.Gato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatoRepository extends JpaRepository<Gato, Long> {
    Gato findByNome(String nome);
}
