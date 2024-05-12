# Project Title: Author Management System

## Descripción

El sistema de gestión de autores es una aplicación diseñada para facilitar la administración de información académica relacionada con autores de diversas universidades. Utiliza tecnologías como Hibernate para la persistencia de datos y Maven para la gestión de dependencias y construcción del proyecto.

## Características

- Búsqueda y gestión de autores por universidad.
- Operaciones CRUD completas para la entidad `Author`.
- Interacción con API externa para obtener datos de autores.

## Tecnologías Utilizadas

- Java
- Maven
- Hibernate
- MySQL
- Gson para el manejo de JSON
- JitPack para repositorios adicionales

## Configuración del Proyecto

### Requisitos

- Java JDK 22
- Maven 3.6 o superior
- MySQL_Connector 8.0.28 o superior

## Uso Básico

El sistema de gestión de autores permite realizar búsquedas y gestionar información de autores académicos a través de una interfaz de consola.

### Buscar Autores por Universidad

Para buscar autores asociados con una universidad específica, sigue estos pasos:

1. **Iniciar la aplicación**:
   Ejecuta el sistema desde la línea de comando utilizando el siguiente comando:
   ```bash
   java -jar target/nao-articles-1.0-SNAPSHOT.jar
   ```

2. **Introducir el nombre de la universidad:**
   Al iniciar, el sistema te pedirá que introduzcas el nombre de la universidad de la cual deseas obtener autores:
   ```bash
   Please insert the name of the university to get the authors from:
   ```
   Escribe el nombre de la universidad y presiona Enter.

3. **Visualización de resultados:**
   El sistema recuperará y mostrará en consola los autores de la universidad especificada. Cada autor se presentará con sus detalles correspondientes.

   *Ejemplo de salida*
   ```bash
   Please insert the name of the university to get the authors from: 
   unam
   
   Authors from unam extracted from the API:
   
   Author: Rafael Lozano
   Author ID: 7Fl-fjIAAAAJ
   Link: https://scholar.google.com/citations?hl=en&user=7Fl-fjIAAAAJ
   Affiliations: Professor, University of Washington Institute for Health Metrics and Evaluation, UNAM FAC …
   Email: Verified email at uw.edu
   
   Author: Leonid Serkin
   Author ID: fKlpWLcAAAAJ
   Link: https://scholar.google.com/citations?hl=en&user=fKlpWLcAAAAJ
   Affiliations: Instituto de Ciencias Nucleares, Universidad Nacional Autónoma de México
   Email: Verified email at correo.nucleares.unam.mx

   ...

   Authors from the database:
   
   Id: 1
   Author: Rafael Lozano
   Author ID: 7Fl-fjIAAAAJ
   Link: https://scholar.google.com/citations?hl=en&user=7Fl-fjIAAAAJ
   Affiliations: Professor, University of Washington Institute for Health Metrics and Evaluation, UNAM FAC …
   Email: Verified email at uw.edu
   
   Id: 2
   Author: Leonid Serkin
   Author ID: fKlpWLcAAAAJ
   Link: https://scholar.google.com/citations?hl=en&user=fKlpWLcAAAAJ
   Affiliations: Instituto de Ciencias Nucleares, Universidad Nacional Autónoma de México
   Email: Verified email at correo.nucleares.unam.mx

   ...
   ```
