FROM maven:3.8.7-openjdk-18 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:18

WORKDIR /app

COPY --from=builder /app/target/expenseTracker-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]