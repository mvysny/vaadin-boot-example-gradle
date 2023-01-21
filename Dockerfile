# Allows you to run this app easily as a docker container.
# See README.md for more details.
#
# 1. Build the image with: docker build --no-cache -t test/vaadin-boot-example-gradle:latest .
# 2. Run the image with: docker run --rm -ti -p8080:8080 test/vaadin-boot-example-gradle
#
# Uses Docker Multi-stage builds: https://docs.docker.com/build/building/multi-stage/

# The "Build" stage. Copies the entire project into the container, into the /app/ folder, and builds it.
FROM openjdk:11 AS BUILD
COPY . /app/
WORKDIR /app/
RUN ./gradlew clean test --no-daemon --info --stacktrace
RUN ./gradlew build -Pvaadin.productionMode --no-daemon --info --stacktrace
WORKDIR /app/build/distributions/
RUN ls -la
RUN unzip app.zip
# At this point, we have the app (executable bash scrip plus a bunch of jars) in the
# /app/build/distributions/app/ folder.

# The "Run" stage. Start with a clean image, and copy over just the app itself, omitting gradle, npm and any intermediate build files.
FROM openjdk:11
COPY --from=BUILD /app/build/distributions/app /app/
WORKDIR /app/bin
EXPOSE 8080
ENTRYPOINT ./app

