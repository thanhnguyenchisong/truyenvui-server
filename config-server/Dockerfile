#Should build your java image - which is match with your openjdk development
FROM eclipse-temurin:17

#Meta data
LABEL mentainer="thanhnguyenchisong@gmail.com"

WORKDIR /app

COPY --chown=1001:0 target/config-server-*.jar config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]