#Comenzamos descargando JAVA para el container
FROM openjdk:17-jdk-slim

#Informacion de quien mantiene esta imagen
MAINTAINER ianproject.com

#Copiamos el JAR de la app dentro de mi container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Cuando se quieran crear una imagen de docker se ejecute el entrypoint
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]