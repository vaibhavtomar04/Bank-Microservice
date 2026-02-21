#start with a base image containing java runtime
FROM openjdk:21-rc-jdk-bullseye

LABEL "org.opencontainers.image.author"="vaibhav_tomar"

#Adding application jar to image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#exceute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]

