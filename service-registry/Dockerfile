#Should build your java image - which is match with your openjdk development
FROM eclipse-temurin:17

#Meta data
LABEL mentainer="thanhnguyenchisong@gmail.com"

WORKDIR /app

COPY ./target/service-registry-*.jar app/service-registry.jar

EXPOSE 8761

ENTRYPOINT["java", "-jar", "service-registry.jar"]

