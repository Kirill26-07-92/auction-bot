FROM openjdk:11.04_11-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/auction-bot-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} /auction-bot.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/auction-bot.jar"]