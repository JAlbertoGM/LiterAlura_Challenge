package com.literalura.desafio.service;

public interface IConvierteDatos {
    //Usamos el metodo Obtener datos del tipo generico para poder recibir los datos que la API nos mande y crear la clase que necesitemos
    <T> T obtenerDatos(String json, Class<T> clase);
}
