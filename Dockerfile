FROM openjdk:11-oracle

EXPOSE 8070

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} playlist_management-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "playlist_management-0.0.1-SNAPSHOT.jar"]
