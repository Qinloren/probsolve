FROM amazoncorretto:17-alpine

LABEL authors="qinloren"

WORKDIR /app

COPY src/main/resources/application.yml application.yml

COPY target/probsolve-1.0.0-SANPSHOT.jar app.jar

EXPOSE 32223

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/application.yml"]