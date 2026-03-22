FROM eclipse-temurin:21-jdk-noble AS build
ENV JAVA_HOME=/opt/java/openjdk \
    PATH="$JAVA_HOME/bin:$PATH"
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-noble
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
