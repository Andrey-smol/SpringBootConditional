FROM astita/openjdk17_jdk-alpine
LABEL authors="Home-PC"

EXPOSE 8081
ADD target/SpringBootConditional-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java", "-jar", "/myapp.jar"]