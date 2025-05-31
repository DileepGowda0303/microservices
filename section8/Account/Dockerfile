#start with base image container Java runtime 
FROM openjdk:17-jdk-slim

MAINTAINER Dileep.com

#Add the Application's jar to image
COPY target/*.jar  account.jar

#Execute the Application
ENTRYPOINT [ "java","-jar","account.jar" ]
