FROM openjdk:21-jdk-slim
WORKDIR /build
COPY target/kata-0.0.1.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","kata-0.0.1.jar"]