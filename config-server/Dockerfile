#Should build your java image - which is match with your openjdk development
FROM eclipse-temurin:17

#Meta data
LABEL mentainer="thanhnguyenchisong@gmail.com"

WORKDIR /app

COPY ./target/config-server-*.jar app/config-server.jar

ENTRYPOINT["java", "-jar", "config-server.jar"]

