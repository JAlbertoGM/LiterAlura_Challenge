package com.literalura.desafio.service;

import com.literalura.desafio.model.Libro;
import com.literalura.desafio.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodos(){
        return libroRepository.findAll();
    }
    public Libro guardar(Libro libro){
        return libroRepository.save(libro);
    }
}
