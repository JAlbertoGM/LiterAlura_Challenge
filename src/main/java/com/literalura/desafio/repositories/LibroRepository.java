package com.literalura.desafio.repositories;

import com.literalura.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Optional<Libro> findByTitulo(String titulo);
}
