# LiterAlura Challenge

Este proyecto es una aplicación en Java que interactúa con la API Gutendex para realizar búsquedas de libros y autores, ofreciendo diversas funcionalidades basadas en un menú interactivo.

## Descripción
El programa permite a los usuarios buscar libros y autores utilizando la API de Gutendex. Ofrece un menú con opciones para buscar libros por nombre, filtrar autores por nombre o fechas, buscar libros por idioma y listar los libros y autores encontrados. Además, se utiliza una estructura modular con clases y servicios para manejar la conexión y los datos de la API.

## Funcionalidades
- **Buscar libro por nombre**: Encuentra un libro en la API por su título y muestra detalles como el autor, idioma y número de descargas.
- **Buscar autor por nombre**: Busca en la lista de autores registrados los que coincidan con un nombre o palabra clave.
- **Buscar libros por idioma**: Filtra los libros por un idioma específico (por ejemplo, `es` para español).
- **Buscar autor por fechas**: Identifica autores que estaban vivos en un año dado.
- **Listar libros buscados**: Muestra todos los libros que han sido encontrados durante la sesión actual.
- **Salir del programa**: Cierra la aplicación.

## Requisitos
- Java 17 o superior.
- Acceso a Internet (para consumir la API de Gutendex).
- Dependencias externas como Jackson para manejo de JSON.

# LiterAlura Challenge

## Instalación
Clona este repositorio:

```bash
# Copiar código
git clone https://github.com/JAlbertoGM/LiterAlura_Challenge.git
```

Asegúrate de tener Java configurado en tu sistema.

Importa el proyecto en tu IDE favorito (como IntelliJ IDEA o Eclipse).

## Estructura del Proyecto
- **com.literalura.desafio.principal.Principal**: Clase principal que contiene el menú interactivo y coordina las funciones.
- **com.literalura.desafio.service.ConsumoAPI**: Maneja la conexión con la API.
- **com.literalura.desafio.service.ConvierteDatos**: Convierte los datos JSON obtenidos en objetos Java.
- **com.literalura.desafio.model**: Contiene clases para representar la información de libros y autores.

## Uso
Ejecuta la clase **Principal** para iniciar el programa.

1. Selecciona una opción del menú para interactuar con la API.
2. Ingresa la información solicitada (nombre del libro, autor, idioma o año) según la opción elegida.
3. Revisa los resultados en la consola.

## Ejemplo de Uso
------------------------------------
## Menú Interactivo

--------------Reto LiterAlura---------------
***********************************************
1) Buscar libro por nombre
2) Buscar autor por nombre
3) Buscar libros por idiomas
4) Buscar autor por fechas
5) Libros buscados
0) Salir
***********************************************
Selecciona una opción:


------------------------
## Búsqueda de Libro por Nombre
------------------------
Ingrese el nombre del libro: Don Quixote

Libro encontrado:

ID: 12345

Título: Don Quixote

Autor(es): [Miguel de Cervantes]

Lenguaje: [es]

Número de descargas: 5000

------------------------
## API Utilizada
------------------------

Se utiliza la API pública de Gutendex para obtener datos sobre libros y autores. Consulta la documentación oficial de la API aquí.
