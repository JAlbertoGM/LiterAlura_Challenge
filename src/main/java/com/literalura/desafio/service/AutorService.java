package com.literalura.desafio.service;

import com.literalura.desafio.model.Autor;
import com.literalura.desafio.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodos(){
        return autorRepository.findAll();
    }
    //Creamos
    public Autor guardar(Autor autor){
        return autorRepository.save(autor);
    }
    /*
    public Autor buscarNombre(String nombre){
        return autorRepository.findBy(nombre);
    }*/
}
