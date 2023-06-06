FROM openjdk:17-alpine
WORKDIR /app

COPY target/accounts-1.1.2.jar accounts-1.1.2.jar

EXPOSE 8080

CMD ["java", "-jar", "accounts-1.1.2.jar"]