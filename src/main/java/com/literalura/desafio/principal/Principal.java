package com.literalura.desafio.principal;

import com.literalura.desafio.model.*;
import com.literalura.desafio.repositories.AutorRepository;
import com.literalura.desafio.repositories.LibroRepository;
import com.literalura.desafio.service.ConsumoAPI;
import com.literalura.desafio.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class Principal {

    //@Autowired
    //private LibroRepository libroRepository;

    //@Autowired
    //private AutorRepository autorRepository;

    private LibroRepository libroRepository = null;

    @Autowired
    public Principal(LibroRepository repository) {
        this.libroRepository = libroRepository;
    }
    //Creamos como constante la url para conectar con la API
    private static final String URL_BASE = "https://gutendex.com/books/";
    //Instanciamos el objeto de consumo api para la conexion que se genera
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    //Instanciamos el objeto de convierte datos para poder manejar la informacion recibida
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    // Creamos la lista para guardar los libros y los autores
    List<DatosLibros> listaLibros = new ArrayList<DatosLibros>();
    List<DatosAutor> listaAutores = new ArrayList<>();

    Scanner sc = new Scanner(System.in);
    int opc = 0;
    //Aqui se hara el menu para mostrar al usuario
    public void muestraMenu(){

        //System.out.println(json);


        do{
            System.out.print("--------------Reto LiterAlura---------------\n\n" +
                    "***********************************************\n" +
                    "1) Buscar libro por nombre\n" +
                    "2) Buscar autor por nombre\n" +
                    "3) Buscar libros por idiomas\n" +
                    "4) Buscar autores entre fechas\n" +
                    "5) Libros buscados\n" +
                    "0) Salir\n" +
                    "***********************************************\n"+
                    "Selecciona una opcion : ");

            //opc = sc.nextInt();

            while (!sc.hasNextInt()) {  // Validación para asegurarse de que se introduce un número
                System.out.println("Entrada no válida. Por favor, introduce un número entre 1 y 5.");
                sc.next(); // Limpiar entrada inválida
            }
            opc = sc.nextInt();//Leemos dato entero

            switch (opc){
                case 1:
                    muestraLibroNombre();
                    break;
                case 2:
                    buscarAutor();
                    break;
                case 3:
                    idiomaLibros();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    System.out.println("opc5");
                    break;
            }

        }while (opc !=0);
        System.out.println("Fin del programa");
    }

    //funcion para buscar libro por nombre
    public void muestraLibroNombre() {
        var json = consumoAPI.obtenerDatos(URL_BASE);

        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = sc.next();

        // Enviamos la consulta con el nombre
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datosBusqueda = convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibros libro = libroBuscado.get();

            if (libroRepository.findByTitulo(libro.titulo()).isPresent()) {
                System.out.println("El libro ya está registrado en la base de datos.");
            } else {
                Libro nuevoLibro = new Libro();
                nuevoLibro.setTitulo(libro.titulo());
                nuevoLibro.setIdiomas(libro.idiomas());
                nuevoLibro.setNumeroDescargas(libro.numeroDescargas());

                List<Autor> autores = new ArrayList<>();
                for (DatosAutor datosAutor : libro.autor()) {
                    Autor autor = AutorRepository.findByNombre(datosAutor.nombre())
                            .orElse(new Autor(datosAutor.nombre(), datosAutor.fechaNacimiento(), datosAutor.fechaFallecimiento()));
                    autores.add(autor);
                }
                nuevoLibro.setAutores(autores);

                libroRepository.save(nuevoLibro);
                System.out.println("Libro y autor(es) almacenados exitosamente.");
            }
        } else {
            System.out.println("Libro No encontrado");
        }
    }



    public void buscarAutor(){
        System.out.println("Ingrese el nombre del autor: ");
        var nombreAutor = sc.next();
        // Filtrar y mostrar autores que contengan la palabra clave ignorando mayúsculas/minúsculas
        List<DatosAutor> autoresCoincidentes = listaAutores.stream()
                .filter(a -> a.nombre().toUpperCase().contains(nombreAutor.toUpperCase()))
                .toList();

        // Mostrar resultados
        if (!autoresCoincidentes.isEmpty()) {
            System.out.println("Autores encontrados que coinciden con '" + nombreAutor + "':");
            for (DatosAutor autor : autoresCoincidentes) {
                System.out.println("Nombre del autor: " + autor.nombre());
                System.out.println("Fecha de nacimiento: " + autor.fechaNacimiento());
                System.out.println("Fecha de fallecimiento: " + autor.fechaFallecimiento());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("No se encontraron autores que coincidan con '" + nombreAutor + "'");
        }
    }

    public void idiomaLibros() {

        System.out.println("*****************\n" +
                "es = Español\n" +
                "en = Ingles\n" +
                "fr = Frances\n" +
                "*****************");

        System.out.println("Ingrese idioma del libro: ");
        var idioma = sc.next();

        // Filtrar libros que contengan el idioma especificado
        List<DatosLibros> librosPorIdioma = listaLibros.stream()
                .filter(libro -> libro.idiomas().contains(idioma))
                .toList();

        // Mostrar los resultados
        if (!librosPorIdioma.isEmpty()) {
            System.out.println("Libros encontrados en el idioma '" + idioma + "':");
            for (DatosLibros libro : librosPorIdioma) {
                System.out.println("ID: " + libro.ide());
                System.out.println("Título: " + libro.titulo());
                System.out.println("Lenguaje: " + libro.idiomas());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("No se encontraron libros en el idioma '" + idioma + "'");
        }
    }

    public void autoresVivos() {

        System.out.println("Ingrese el año de busqueda: ");
        var anio = sc.nextInt();
        // Filtrar autores que estaban vivos en el año especificado
        List<DatosAutor> autoresEnVida = listaAutores.stream()
                .filter(autor -> {
                    int nacimiento = Integer.parseInt(autor.fechaNacimiento());
                    int fallecimiento = autor.fechaFallecimiento() == null || autor.fechaFallecimiento().isEmpty()
                            ? Integer.MAX_VALUE
                            : Integer.parseInt(autor.fechaFallecimiento());
                    return anio >= nacimiento && anio <= fallecimiento;
                })
                .toList();

        // Mostrar los resultados
        if (!autoresEnVida.isEmpty()) {
            System.out.println("Autores vivos en el año " + anio + ":");
            for (DatosAutor autor : autoresEnVida) {
                System.out.println("Nombre del autor: " + autor.nombre());
                System.out.println("Fecha de nacimiento: " + autor.fechaNacimiento());
                System.out.println("Fecha de fallecimiento: " + autor.fechaFallecimiento());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        }
    }

}
