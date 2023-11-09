#Should build your java image - which is match with your openjdk development
FROM eclipse-temurin:17

#Meta data
LABEL mentainer="thanhnguyenchisong@gmail.com"

WORKDIR /app

COPY ./target/api-gateway-*.jar app/api-gateway.jar

ENTRYPOINT["java", "-jar", "api-gateway.jar"]

