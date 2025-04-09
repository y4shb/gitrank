FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/gitrank-*.jar gitrank.jar
EXPOSE 8080
CMD ["java", "-jar", "gitrank.jar"]