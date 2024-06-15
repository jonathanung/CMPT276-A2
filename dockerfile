# Use the official Maven image to build the app
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use the official Tomcat image to run the app
FROM tomcat:9.0-jdk17-openjdk-slim
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/target/asn2-0.0.1-SNAPSHOT.war ./ROOT.war

EXPOSE 8080

CMD ["wait-for-it.sh", "postgres:5432", "--timeout=60", "--strict", "--", "catalina.sh", "run"]
