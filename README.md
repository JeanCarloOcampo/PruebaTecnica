# Carvajal - Sistema de Lista de Deseos

API REST desarrollada en Spring Boot que permite a los clientes gestionar su lista de productos deseados, con histórico de cambios y notificación de disponibilidad de stock.

## Tecnologías

- Java 21
- Spring Boot 3.5.14
- Spring Data JPA
- MySQL 
- Lombok

## Requisitos previos

- JDK 21 instalado
- MySQL Server corriendo en el puerto 8080
- Maven (se incluye el wrapper `mvnw`)

## Configuración de la base de datos

La base de datos se crea automáticamente al iniciar la aplicación gracias a la configuración `createDatabaseIfNotExist=true`.

Las tablas se crean mediante el archivo `src/main/resources/schema.sql` y los datos de prueba se cargan desde `src/main/resources/data.sql`.