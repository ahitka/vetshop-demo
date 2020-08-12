FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests
FROM maven:3.6.3-jdk-11-slim
COPY --from=build /workspace/target/vetshop-demo-docker.jar vetshop-demo-docker.jar
CMD ["java", "-jar", "vetshop-demo-docker.jar", "--server.port=${PORT:8080}"]
