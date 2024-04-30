### **Uso del Sistema para Consultar Autores**

## Método `getAuthorsByUniversity`

Este método es la principal función del sistema. Se encuentra en la clase `Search` y su propósito es obtener una lista
de autores de una universidad específica utilizando la API de SerpApi.

### Parámetros

El método `getAuthorsByUniversity` espera un parámetro:

- `university`: Una cadena de texto que representa el nombre de la universidad de la que se quieren obtener los autores.

### Retorno

El método devuelve un objeto `AuthorResult`. Este objeto contiene dos campos:

- `authors`: Una lista de objetos `Author`, donde cada `Author` representa a un autor de la universidad especificada.
  Cada objeto `Author` tiene los siguientes campos:
    - `name`: El nombre del autor.
    - `authorId`: El ID del autor.
    - `link`: El enlace al perfil del autor.
    - `affiliations`: Las afiliaciones del autor.
    - `email`: El correo electrónico del autor.

- `message`: Una cadena de texto que representa un mensaje sobre el resultado de la búsqueda. Este mensaje puede indicar
  si la búsqueda fue exitosa o si hubo algún error.

## Variables de Entorno

Para un correcto funcionamiento del sistema se requiere la creación de un archivo nombrado como "*.env*" en el
directorio del proyecto 'src/main/resources' que incluirá el valor de la variable "API_KEY".

### Ejemplo de uso

```java
Search search = new Search();
AuthorResult result = search.getAuthorsByUniversity("UNAM");
result.authors().forEach(author -> System.out.println(
    "Author: " + author.getName() + "\n" +
    "Author ID: " + author.getAuthorId() + "\n" +
    "Link: " + author.getLink() + "\n" +
    "Affiliations: " + author.getAffiliations() + "\n" +
    "Email: " + author.getEmail() + "\n"
));
