# Allows you to run this app easily in a docker image.
# See README.md for more details.
#
# 1. Build the image with: docker build --no-cache -t test/vaadin-embedded-jetty-gradle:latest .
# 2. Run the image with: docker run --rm -ti -p8080:8080 test/vaadin-embedded-jetty-gradle
#
# Uses Docker Multi-stage builds: https://docs.docker.com/build/building/multi-stage/

# The "Build" stage. Copies the entire project into the container, into the /vaadin-embedded-jetty-gradle/ folder, and builds it.
FROM openjdk:11 AS BUILD
COPY . /vaadin-embedded-jetty-gradle/
WORKDIR /vaadin-embedded-jetty-gradle/
RUN ./gradlew clean test --no-daemon --info --stacktrace
RUN ./gradlew build -Pvaadin.productionMode --no-daemon --info --stacktrace
WORKDIR /vaadin-embedded-jetty-gradle/build/distributions/
RUN ls -la
RUN unzip vaadin-embedded-jetty-gradle.zip
# At this point, we have the app (executable bash scrip plus a bunch of jars) in the
# /vaadin-embedded-jetty-gradle/build/distributions/vaadin-embedded-jetty-gradle/ folder.

# The "Run" stage. Start with a clean image, and copy over just the app itself, omitting gradle, npm and any intermediate build files.
FROM openjdk:11
COPY --from=BUILD /vaadin-embedded-jetty-gradle/build/distributions/vaadin-embedded-jetty-gradle /app/
WORKDIR /app/bin
EXPOSE 8080
ENTRYPOINT ./vaadin-embedded-jetty-gradle

