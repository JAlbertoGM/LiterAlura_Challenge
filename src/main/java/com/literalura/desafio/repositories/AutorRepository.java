package com.literalura.desafio.repositories;

import com.literalura.desafio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    static Optional<Autor> findByNombre(String nombre) {
        return null;
    }
}
