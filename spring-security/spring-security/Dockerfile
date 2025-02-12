#UN DOCKERFILE SE USA PARA CREAR UNA IMAGEN DE DOCKER.

#FROM sirve para especificar la imagen base desde la cual partiremos, para no hacerlo desde cero. Alpine es una distro de Linux muy ligera.
FROM eclipse-temurin:17-jdk-alpine

#COPY sirve para copiar el .jar generado por la aplicación, a la imagen, pero con el nombre app.jar. target/*.jar es la ubicación de nuestro .jar.
COPY target/*.jar app.jar

#ENTRYPOINT sirve para especificar el primer comando que se ejecutará en el contenedor para ejecutar el .jar: java -jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#Al final ejecutas "docker build -t my-app:v1 ." estando dentro del proyecto.