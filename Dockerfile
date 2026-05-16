FROM gradle:8.7-jdk21 AS build

WORKDIR /app

COPY gradle.properties build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle

RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

COPY src /app/src

RUN ./gradlew quarkusBuild -x test --no-daemon

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/build/quarkus-app/ /app/

EXPOSE 9000

CMD ["java", "-jar", "/app/quarkus-run.jar"]