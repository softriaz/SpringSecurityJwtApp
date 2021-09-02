# SpringSecurityJwtApp

Application Documentation

This proof of concept was done using the following technology:

JDK 11

Spring Boot

H2 In memory Database

Docker

This sample application is packed and uploaded into docker hub for easy installaion

Prerequisite: Required docker installed in your local machine.

Installation Procedure:

Step 1: Clone the docker image uploaded into docker hub using the below command:

docker pull softriazin/jwt-secured-boot-application:latest

Step 2: Run the cloned docker image using the below command in your docker container

docker run --name myApp -p 8081:8081 jwt-secured-boot-application .
 
