# P1-DSS
Aplicación CRUD básica de Carrito de Compra
# P1_DSS — Gestión de productos y carrito de compra

## 1. Descripción del proyecto

P1_DSS es una aplicación web desarrollada con Spring Boot para la gestión de productos y un carrito de compra.

La aplicación permite administrar los productos disponibles y realizar operaciones sobre ellos mediante una interfaz web. También incorpora un carrito de compra y un sistema de seguridad para controlar el acceso a la aplicación.

## 2. Tecnologías utilizadas

* **Java 17:** lenguaje de programación.
* **Spring Boot:** configuración y ejecución de la aplicación.
* **Spring MVC:** gestión de las peticiones HTTP y navegación entre páginas.
* **Spring Data JPA:** acceso y persistencia de los datos.
* **Hibernate:** implementación de la persistencia mediante JPA.
* **Thymeleaf:** generación de las páginas HTML dinámicas.
* **Spring Security:** gestión de la seguridad de la aplicación.
* **H2 Database:** base de datos utilizada para almacenar los productos.
* **Maven:** gestión de dependencias y construcción del proyecto.

## 3. Funcionalidades principales

### Gestión de productos

La aplicación permite:

* Consultar el listado de productos.
* Añadir nuevos productos.
* Editar productos existentes.
* Eliminar productos.
* Buscar productos por nombre.
* Filtrar productos por precio.

Cada producto dispone de un identificador, un nombre y un precio.

### Carrito de compra

La aplicación incorpora un carrito que permite gestionar los productos seleccionados por el usuario.

### Seguridad

La aplicación integra Spring Security para proteger el acceso a las funcionalidades configuradas.

## 4. Estructura del proyecto

El proyecto sigue una organización por capas para separar las responsabilidades de la aplicación:

* **model:** clases que representan las entidades del dominio, como `Producto`.
* **repository:** interfaces para el acceso a los datos mediante Spring Data JPA.
* **service:** lógica de negocio y operaciones relacionadas con los productos y el carrito.
* **controller:** gestión de las peticiones HTTP y comunicación entre la interfaz y la lógica de negocio.
* **templates:** plantillas HTML procesadas por Thymeleaf.
* **static:** recursos estáticos, si existen, como CSS, JavaScript e imágenes.

La clase principal de Spring Boot se encarga de iniciar la aplicación.

## 5. Requisitos previos

Para ejecutar el proyecto se necesita:

* Java JDK 17.
* Spring Tool Suite (STS), Eclipse con soporte para Maven o un IDE compatible.
* Acceso a los repositorios Maven para descargar las dependencias, si todavía no están disponibles localmente.

## 6. Instalación y ejecución

### Importar el proyecto

1. Extraer el archivo ZIP del proyecto.
2. Abrir Spring Tool Suite.
3. Seleccionar `File → Import → Maven → Existing Maven Projects`.
4. Seleccionar la carpeta extraída que contiene el archivo `pom.xml`.
5. Finalizar la importación y esperar a que Maven resuelva las dependencias.

### Ejecutar la aplicación

1. Localizar la clase principal `P1DssApplication.java`.
2. Hacer clic derecho sobre ella.
3. Seleccionar `Run As → Spring Boot App`.
4. Esperar a que la aplicación se inicie correctamente.

La aplicación utiliza el puerto configurado en Spring Boot. Si se mantiene la configuración habitual, estará disponible en:

http://localhost:8080

## 7. Base de datos

La aplicación utiliza H2 como sistema de gestión de base de datos y Spring Data JPA para acceder a los datos.

La base de datos se configura mediante los archivos de configuración de Spring Boot. Si se utiliza persistencia en archivo, deben conservarse los archivos de base de datos necesarios y comprobarse que la ruta configurada sea válida en el equipo donde se ejecute el proyecto.

Si la aplicación requiere datos iniciales, estos deberán estar disponibles mediante la base de datos entregada o el mecanismo de inicialización configurado.

## 8. Gestión de dependencias

Las dependencias del proyecto están declaradas en `pom.xml`. Maven se encarga de resolverlas y descargarlas cuando sea necesario.

Para actualizar las dependencias desde STS:

`Clic derecho sobre el proyecto → Maven → Update Project...`

## 9. Autoría

Proyecto desarrollado como parte de la asignatura correspondiente al proyecto P1_DSS.

## 10. Enlace al Repositorio en Github

https://github.com/Yasmine9MS/P1-DSS.git