# Sistema de Gestión Logística

Este proyecto implementa una API REST para gestión de envíos terrestres y marítimos utilizando arquitectura hexagonal y Spring Boot.

## Estructura del Proyecto
```bash
backend-logistic-app/
├── src/
│   ├── main/
│   │   ├── java/com/logistics/
│   │   │   ├── application/
│   │   │   │   └── service/
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   └── port/
│   │   │   │       ├── in/
│   │   │   │       └── out/
│   │   │   ├── infrastructure/
│   │   │   │   ├── adapter/
│   │   │   │   │   ├── in/
│   │   │   │   │   │   ├── controller/
│   │   │   │   │   │   └── dto/
│   │   │   │   │   └── out/
│   │   │   │   │       ├── persistence/
│   │   │   │   │   │   │   ├── adapter/
│   │   │   │   │   │   │   └── repository/
│   │   │   │   ├── config/
│   │   │   │   ├── exception/
│   │   │   │   └── security/
│   │   │   └── BackendLogisticAppApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docs/
│   ├── database/
│   │   ├── schema.dbml
│   │   └── schema.png
│   └── diagrams/
│       ├── architecture.puml
│       └── plantuml-diagram.svg
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── init.sql
└── pom.xml
```


## Requisitos Previos

- **Docker** y **Docker Compose** instalados en tu máquina.

## Ejecución con Docker

El proyecto está completamente preparado para ejecutarse usando contenedores Docker. A continuación se detalla cómo desplegar el backend junto con su base de datos PostgreSQL.

1. **Clonar el Repositorio principal**  
   Primero, clona el repositorio principal y asegúrate de inicializar los submódulos de Git, ya que tu proyecto depende de ellos.

   ```bash
     git clone https://github.com/kevin3080/logistic-app.git
     cd logistic-app
     git submodule update --init --recursive
   ```

2. **Levantar los Servicios con Docker Compose**  

   Navega a la carpeta del backend y usa el siguiente comando para construir las imágenes y levantar los servicios.

   ```bash
     cd backend-logistic-app
     docker compose up --build
   ```

   Esto creará y levantará los contenedores necesarios:
   - **Backend API** disponible en `http://localhost:8080`
   - **Base de datos PostgreSQL** disponible en `127.0.0.1:5433` con los valores iniciales definidos en `init.sql`.

3. **Verificar el Estado de los Contenedores**  
   Puedes verificar los contenedores levantados con el siguiente comando:

   ```bash
     docker ps
   ```

   Asegúrate de que ambos servicios (`backend-logistic-app` y `postgres:15-alpine`) estén en ejecución.

4. **Detener los Contenedores**  
   Para detener los contenedores, usa:

   ```bash
     docker compose down
   ```

   Esto eliminará los contenedores, pero la información en volúmenes persistentes no se perderá.

     > **Nota:**  
     > Si por alguna razon quieres reiniciar los valores de la base de datos a los iniciales de `init.sql` entonces debes usar `docker compose down -v` para eliminar el volumen de la base de datos y volver a subir la aplicacion usando `docker compose up ---build` para ejecutar nuevamente la migracion


---

## Variables de Entorno

Las variables de entorno clave se encuentran descritas en el archivo `docker-compose.yml`. Los valores predeterminados incluyen:

`yaml environment:`
 - POSTGRES_USER: postgres
 - POSTGRES_PASSWORD: postgres
 - POSTGRES_DB: logistic_db

## Características del Dockerfile

El proyecto utiliza un **Dockerfile multi-stage** para construir y ejecutar el backend de manera eficiente:

1. **Etapa de Build**:
   - Utiliza una imagen Maven para compilar el código y generar el archivo `.jar`.

2. **Etapa de Runtime**:
   - Utiliza una imagen ligera de OpenJDK para ejecutar el archivo `.jar` previamente generado.

## API y Acceso

1. **API Base URL**:  
   La API estará disponible en `http://localhost:8080`.

2. **Documentación Swagger**:  
   La documentación Swagger está disponible en `http://localhost:8080/swagger-ui.html`.


## Arquitectura

El proyecto sigue una arquitectura hexagonal (ports and adapters) con tres capas principales:

1. **Domain**: Contiene las entidades del negocio y los puertos (interfaces)
2. **Application**: Implementa la lógica de negocio
3. **Infrastructure**: Contiene adaptadores para REST API y persistencia

## Diagramas del Proyecto

### Estructura de la Base de Datos
El diagrama de la base de datos se encuentra en `docs/database/schema.dbml`. 
Puedes visualizarlo en [dbdiagram.io](https://dbdiagram.io) copiando el contenido del archivo.  
Los datos iniciales se cargan automáticamente desde `init.sql`.

## Base de Datos

1. **Información Predeterminada**:  
   La base de datos PostgreSQL se inicializa con los datos de ejemplo disponibles en `init.sql`. Este script crea tablas y registra datos básicos para pruebas.

2. **Conexión a la Base de Datos**:  
   Puedes conectarte a la base de datos localmente utilizando un cliente como **DBeaver** o **pgAdmin** con las siguientes credenciales:

   - **Host**: 127.0.0.1 
   - **Puerto**: 5433
   - **Usuario**: postgres
   - **Contraseña**: postgres
   - **Base de Datos**: logistic_db
> **Nota Importante:**  
> Usa `127.0.0.1` como host para asegurar la conexión. Aunque `localhost` generalmente funciona, en algunos entornos de Docker puede haber problemas de resolución de nombres. El puerto `5433` es el que se expone en tu máquina local (`host`), y Docker se encarga de redirigir el tráfico al puerto interno del contenedor (`5432`).



### Arquitectura del Sistema
El diagrama de arquitectura se encuentra en `docs/diagrams/architecture.puml`.
Puedes visualizarlo usando cualquier editor compatible con PlantUML como:
- Plugin de IntelliJ IDEA
- Visual Studio Code con extensión PlantUML
- [PlantUML Web Server](http://www.plantuml.com/plantuml/uml/)

## Autenticación

La API utiliza JWT para autenticación. Para obtener un token:

```bash
curl -X POST http://localhost:8080/authenticate
-H "Content-Type: application/json"
-d '{"username":"admin", "password":"password"}'
```

Credenciales por defecto:
- Usuario: admin
- Contraseña: password

Una vez que tengas el token, úsalo en el encabezado Authorization: Bearer YOUR_JWT_TOKEN para todas las solicitudes a los endpoints protegidos.
```bash
Authorization: Bearer YOUR_JWT_TOKEN
```

## Endpoints Principales
A continuación, se detallan los endpoints disponibles con ejemplos de cómo interactuar con ellos usando curl. (puedes usar POSTMAN de igual manera)

Nota: Reemplaza YOUR_JWT_TOKEN con el token real que obtuviste.

### Registrar nuevo cliente
```bash
curl -X POST http://localhost:8080/api/clients \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "name": "Cliente Demo",
           "email": "demo.client@example.com",
           "phone": "+1234567890"
         }'
```

### Listar todos los clientes
```bash
curl -X GET http://localhost:8080/api/clients \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```
### Obtener cliente por ID
```bash
curl -X GET http://localhost:8080/api/clients/CLIENT_UUID \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Actualizar cliente
```bash
curl -X PUT http://localhost:8080/api/clients/CLIENT_UUID \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "name": "Cliente Demo Actualizado",
           "email": "demo.client.updated@example.com",
           "phone": "+1122334455"
         }'
```
### Eliminar cliente
```bash
curl -X DELETE http://localhost:8080/api/clients/CLIENT_UUID \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Envíos Terrestres

### Crear nuevo envío terrestre
```bash
curl -X POST http://localhost:8080/api/land-shipments \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "clientId": "CLIENT_UUID",
           "productId": "PRODUCT_UUID",
           "quantity": 12,
           "registrationDate": "2024-06-20",
           "deliveryDate": "2024-06-25",
           "warehouseId": "WAREHOUSE_UUID",
           "shippingCost": 150.00,
           "vehiclePlate": "ABC123",
           "guideNumber": "LANDSHP001A"
         }'
```

### Listar envíos terrestres
```bash
curl -X GET http://localhost:8080/api/land-shipments \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```
### Obtener envío terrestre por ID
```bash
curl -X GET http://localhost:8080/api/land-shipments/SHIPMENT_UUID \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Actualizar envío terrestre
```bash
curl -X PUT http://localhost:8080/api/land-shipments/SHIPMENT_UUID \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "clientId": "CLIENT_UUID",
           "productId": "PRODUCT_UUID",
           "quantity": 15,
           "registrationDate": "2024-06-20",
           "deliveryDate": "2024-06-30",
           "warehouseId": "WAREHOUSE_UUID",
           "shippingCost": 160.00,
           "vehiclePlate": "DEF456",
           "guideNumber": "LANDSHP001A"
         }'
```

### Obtener envío terrestre por ID
```bash
curl -X DELETE http://localhost:8080/api/land-shipments/SHIPMENT_UUID \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Envíos Marítimos
Sigue la misma convencion que los terrestes

- GET `/api/maritime-shipments` - Listar todos los envíos marítimos
- POST `/api/maritime-shipments` - Crear nuevo envío marítimo
- GET `/api/maritime-shipments/{id}` - Obtener envío por ID
- PUT `/api/maritime-shipments/{id}` - Actualizar envío
- DELETE `/api/maritime-shipments/{id}` - Eliminar envío


## Seguridad

- Autenticación basada en JWT
- Roles: ROLE_USER, ROLE_ADMIN
- Endpoints protegidos requieren token válido
- Contraseñas encriptadas con BCrypt

## Pruebas

Ejecutar pruebas unitarias:
```bash
mvn test
```


