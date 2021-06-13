FROM maven:3-openjdk-11-slim as builder
COPY . ./
RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=builder ./target/kalah*.jar /kalah.jar
CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "kalah.jar"]