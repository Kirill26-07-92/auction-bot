FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/auction-bot-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} /auction-bot.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/auction-bot.jar"]