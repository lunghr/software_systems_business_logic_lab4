FROM amazoncorretto:21-alpine-jdk
#ENTRYPOINT ["sleep", "infinity"]
WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY Dockerfile .
COPY compose.yaml .
COPY .env .

RUN chmod +x ./gradlew \
    && ./gradlew dependencies --no-daemon

COPY src src
RUN ./gradlew clean build -x test --no-daemon
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./build/libs/software_systems_business_logic_lab1-0.0.1-SNAPSHOT.jar"]
