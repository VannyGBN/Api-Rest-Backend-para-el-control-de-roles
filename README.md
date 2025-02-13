#  CRUD para la administración de Roles

Este proyecto es una **API REST Backend** desarrollada con **Spring Boot** que implementa un sistema de gestión de roles y permisos, permitiendo la administración de privilegios que puede tener un rol especifico.

## Características

Se presenta con el CRUD solicitado como parte del examen en el se puede:
- Dar de alta un rol, con nombre, imagen (URL), estatus, fecha (generada en auto.) + una lista de permisos que tiene el rol.
- Buscar los roles por Id.
- Buscar roles por nombre.
- Buscar roles entre fechas de registro.
- Validaciones para evitar que repita un rol.
- Actualizar un rol.
- Eliminar un rol ( y su relacion con sus permisos establecidos)


**Extra** al ejercicio solicitado, se creo un CRUD basico para el manejo de los permisos, y poder crear una lista de la cual se podrán seleccionar para cada rol

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.4.2
- Spring Data JPA
- Maven
- MySQL (MySQL WorkBench 8.0 CE)

  

## Estructura del Proyecto  

**com.mx.AdministracionRoles/**
  - 📂 **controllers/**
    - 📄 RolController.java  
    - 📄 PermisoController.java  
  - 📂 **models/**
    - 📄 RolModel.java  
    - 📄 PermisoModel.java  
  - 📂 **repositories/**
    - 📄 IRolRepository.java  
    - 📄 IPermisoRepository.java  
  - 📂 **services/**
    - 📄 RolService.java  
    - 📄 PermisoService.java  
  - 📂 **dto/**
    - 📄 RolRequestDTO.java
    
## Instalación

1- Clona el repositorio:   
``git clone https://github.com/TU-USUARIO/administracion-roles.git ``  

2- Configura la base de datos en:  
 `` application.properties:``  
 de la siguiente manera: 


``spring.datasource.url=jdbc:mysql://localhost:3306/roles_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true``  
``spring.datasource.username=`` Cambia tu usuario  
``spring.datasource.password=`` Cambia tu contraseña  

3- Ejecutar ``mvn spring-boot:run``

**Nota:**  
- Debes tener creada la Base de datos, si aún no la tienes puedes hacerlo de la siguiente forma: ``create database roles_db;``  
- Debes **descargar** el script de la base de datos e **importarla en MySQL** WorkBench
- Puedes descargar la carpeta completa y abrirla en un entorno directamente sin necesidad de clonar repositorio.  


## Endpoints API

**Para Roles** (Ejercicio solicitado)
- ``GET /rol`` - Obtener todos los roles
- ``POST /rol`` - Crear nuevo rol (validación para evitar que se repida)
- ``GET /rol/{id}`` - Obtener rol por id
- ``PUT /rol/{id}`` - Actualizar rol
- ``DELETE /rol/{id}`` - Eliminar rol
- ``GET /rol/search?nombre=`` - Buscar roles por nombre
- ``GET /rol/search?fechaInicio= &fechaFin`` - Buscar roles por fechas

**Para Permisos** (extra)
- ``GET /rol`` - Lista todos los permisos
- ``POST /rol`` - Crear nuevo permiso
- ``GET /rol/{id}`` - Obtener permiso por ID
- ``DELETE /rol/{id}`` - Eliminar permiso



## Notas: ##
- En el archivo de pruebas POSTMAN, se presentan los Json que permiten hacer uso de las peticiones


  
## Autor  

**[Geovanny Benitez Nava]** - [VannyGBN](https://github.com/VannyGBN)  

  