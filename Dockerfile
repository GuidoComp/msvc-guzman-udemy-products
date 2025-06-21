FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
ADD ./target/products-0.0.1-SNAPSHOT.jar products-server.jar

ENTRYPOINT ["java", "-jar", "products-server.jar"]