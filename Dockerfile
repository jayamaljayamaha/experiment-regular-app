FROM amazoncorretto:17-alpine3.16
COPY build/libs/experiment-regular-app-0.0.1.jar experiment-regular-app-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "experiment-regular-app-0.0.1.jar"]