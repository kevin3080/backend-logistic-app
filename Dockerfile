# Etapa 1: Build
FROM maven:3.8.8-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar solo los archivos necesarios para Maven
COPY pom.xml .
COPY src ./src

# Realiza la construcción del proyecto con maven
RUN mvn -B clean install

# Etapa 2: Runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el archivo .jar del build anterior
COPY --from=build /app/target/backend_logistic_app-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

# Entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
