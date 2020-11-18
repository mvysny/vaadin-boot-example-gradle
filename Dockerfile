# Allows you to run this app easily in a docker image.
# See README.md for more details.

# Build stage
FROM openjdk:11 AS BUILD
COPY build/distributions/vaadin14-embedded-jetty-gradle.zip /app/
WORKDIR /app
RUN unzip vaadin14-embedded-jetty-gradle.zip

# Run stage
FROM openjdk:11
COPY --from=BUILD /app/vaadin14-embedded-jetty-gradle /app/
WORKDIR /app/bin
EXPOSE 8080
ENTRYPOINT ./vaadin14-embedded-jetty-gradle

