FROM maven:3.6.3-jdk-11-openj9 as maven
WORKDIR /build
COPY pom.xml .
COPY src/ /build/src/
RUN mvn package

# Step : Package image
FROM adoptopenjdk:11-jre-hotspot
ENTRYPOINT ["java","-jar","/app/my-app.jar"]
COPY --from=maven /build/target/WishSport-1.0.0-jar-with-dependencies.jar /app/my-app.jar