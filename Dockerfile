FROM maven:3 as builder
WORKDIR /app
COPY . /app
RUN mvn clean install
FROM openjdk:17-alpine
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]