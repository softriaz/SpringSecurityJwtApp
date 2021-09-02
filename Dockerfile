FROM openjdk:11
ADD target/jwt-secured-boot-application.jar jwt-secured-boot-application.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "jwt-secured-boot-application"]