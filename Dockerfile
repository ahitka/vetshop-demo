FROM openjdk:11
EXPOSE 8080
ADD target/vetshop-demo-docker.jar vetshop-demo-docker.jar
ENTRYPOINT ["java", "-jar,","/vetshop-demo-docker.jar"]