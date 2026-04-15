FROM gradle:8.7-jdk21 AS build

WORKDIR /app

COPY gradle.properties build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle

RUN chmod +x gradlew

COPY src /app/src

RUN ./gradlew quarkusBuild -x test --no-daemon

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /deployments

COPY --from=build /app/build/quarkus-app/ /deployments/

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/deployments/quarkus-run.jar"]