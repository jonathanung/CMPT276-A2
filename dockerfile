# Use the official Maven image to build the app
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use the official Tomcat image to run the app
FROM tomcat:9.0-jdk17-openjdk-slim
WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/target/asn2-0.0.1-SNAPSHOT.war ./ROOT.war
