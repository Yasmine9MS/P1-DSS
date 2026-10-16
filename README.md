# P1-DSS
Aplicación CRUD básica de Carrito de Compra

## 1. Descripción del proyecto

P1_DSS es una aplicación web desarrollada con Spring Boot para la gestión de productos y un carrito de compra.

La aplicación permite administrar los productos disponibles y realizar operaciones sobre ellos mediante una interfaz web. También incorpora un carrito de compra y un sistema de seguridad para controlar el acceso a la aplicación.

## 2. Tecnologías utilizadas

* **Java 17:** versión configurada para el proyecto.
* **Spring Boot 4.1.1:** configuración y ejecución de la aplicación.
* **Spring MVC:** gestión de peticiones HTTP y controladores.
* **Thymeleaf:** generación de vistas HTML.
* **Spring Data JPA / Hibernate:** persistencia de datos.
* **H2:** base de datos persistente en archivo y consola de administración.
* **Spring Security:** autenticación, autorización y protección CSRF.
* **Lombok:** reducción de código repetitivo.
* **Maven:** gestión de dependencias y construcción del proyecto.
* **Spring Tools for Eclipse 5.4.0:** entorno de desarrollo (Windows 11).

## 3. Funcionalidades principales

### Gestión de productos
La aplicación permite:

* Consultar el listado de productos (acceso público).
* Añadir, editar y eliminar productos (solo rol `ADMIN`).
* Buscar productos por nombre.
* Filtrar productos por rango de precio.

Cada producto dispone de un identificador, un nombre y un precio.

### Carrito de compra
Los usuarios pueden añadir y eliminar productos del carrito, y se muestra el precio total. El carrito guarda los identificadores de los productos, de modo que siempre refleja los datos actuales del catálogo. Cada usuario dispone de su propio carrito gracias al ámbito de sesión HTTP.

### Seguridad
Spring Security gestiona la autenticación y la autorización mediante los roles `ADMIN` y `USER`. El catálogo y el carrito son públicos; la gestión de productos y el panel de administración (`/admin/**`) están reservados al rol `ADMIN`. La protección CSRF está activa en las peticiones POST.

### Exportación SQL
El administrador puede descargar el catálogo como script SQL desde `/admin/export`. El servicio `DatabaseExportService` genera una sentencia `INSERT` por producto y el archivo se descarga como `products.sql`.

## 4. Estructura del proyecto
El proyecto sigue una organización por capas dentro del paquete `com.example.demo`:

* `config`: configuración de seguridad (`SecurityConfig`).
* `model`: entidades del dominio, como `Producto`.
* `repository`: acceso a datos con Spring Data JPA (`ProductRepo`).
* `service`: lógica de negocio de productos, carrito y exportación.
* `controller`: peticiones HTTP (productos, carrito, administración y login).
* `templates`: plantillas HTML procesadas por Thymeleaf.
* `static`: recursos estáticos.

La clase principal `P1DssApplication` inicia la aplicación.

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

### Acceso a la aplicación

* Catálogo: `http://localhost:8080/products`
* Usuarios de demostración:
  * `admin` / `admin` (rol `ADMIN`)
  * `user` / `user` (rol `USER`)

## 7. Base de datos

La aplicación utiliza H2 en modo archivo y Spring Data JPA, por lo que los datos se conservan al reiniciar la aplicación. Los archivos de la base de datos se guardan en la carpeta `data` del proyecto (`data/productosDB`).

La consola de H2 está disponible en `http://localhost:8080/h2-console` (solo para desarrollo), con estos datos de conexión:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:file:./data/productosDB` |
| Usuario | `sa` |
| Contraseña | `root` |
| Driver | `org.h2.Driver` |

## 8. Gestión de dependencias

Las dependencias del proyecto están declaradas en `pom.xml`. Maven se encarga de resolverlas y descargarlas cuando sea necesario.

Para actualizar las dependencias desde STS:

`Clic derecho sobre el proyecto → Maven → Update Project...`

## 9. Autoría

Proyecto desarrollado por Yasmine Mohamed Sleczek para la asignatura Desarrollo de Sistemas Software basados en Componentes y Servicios.