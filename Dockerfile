FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 8090

CMD ["java", "-jar", "target/twitterbackend-0.0.1-SNAPSHOT.jar"]
