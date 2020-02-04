[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vaadin-flow/Lobby#?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![Build Status](https://travis-ci.org/mvysny/vaadin14-embedded-jetty-gradle.svg?branch=master)](https://travis-ci.org/mvysny/vaadin14-embedded-jetty-gradle)

# Vaadin 14 npm+webpack running in Embedded Jetty using Gradle

A demo project showing the possibility of running a Vaadin 14 app from an
embedded Jetty, as a simple `main()` method.

Both the development and production modes are supported. Also, the project
demoes packaging itself into a zip file containing
a list of jars and a runner script. See "Packaging for production" below
for more details.

The [Vaadin 14 Gradle Plugin](https://github.com/vaadin/vaadin-gradle-plugin)
is used to package all JavaScript dependencies via npm+webpack. See the Plugin
home page for more details.

## Developing

Clone this github repository and import the project to the IDE of your choice
as a Gradle project. You need to have Java JDK 8+ installed.

1. Import the project into your IDE
2. Run `./gradle vaadinPrepareFrontend` in the project, to configure Vaadin for npm mode.
3. Run/Debug the `ManualJetty` class as an application (run the `main()` method).
   The app will use npm to download all javascript libraries (will take a long time)
   and will start in development mode.
   
See [ManualJetty.java](src/main/java/com/vaadin/starter/skeleton/ManualJetty.java)
for details on how Jetty is configured for embedded mode.

### Missing `/src/main/webapp`?

Yeah, since we're not packaging to WAR but to uberjar/zip+jar, the `webapp` folder needs to be
served from the jar itself, and therefore it needs to reside in `src/main/resources/webapp`.

## Packaging for production

To package in production mode:

1. `./gradle vaadinBuildFrontend build`

The project packages itself as a zip file with dependencies. The file is
in `build/distributions/vaadin14-embedded-jetty-gradle.zip`

## Running in production mode

To build&run the zip file:

1. `./gradle vaadinBuildFrontend build`
2. `cd build/distributions/`
3. `unzip vaadin14-embedded-jetty-gradle.zip`
4. `./run`

Head to [localhost:8080/](http://localhost:8080).

## About The Project

Let's look at all files that this project is composed of, and what are the points where you'll add functionality:

| Files | Meaning
| ----- | -------
| [build.gradle](build.gradle) | Gradle build tool configuration files. Gradle is used to compile your app, download all dependency jars and build the zip file
| [.travis.yml](.travis.yml) | Configuration file for [Travis-CI](http://travis-ci.org/) which tells Travis how to build the app. Travis watches your repo; it automatically builds your app and runs all the tests after every commit.
| [.gitignore](.gitignore) | Tells [Git](https://git-scm.com/) to ignore files that can be produced from your app's sources - be it files produced by Gradle, Intellij project files etc.
| [Procfile](Procfile) | Configures Heroku on how your application is launched in the cloud.
| [webpack.config.js](webpack.config.js) | TODO
| [src/main/java](src/main/java) | Place the sources of your app here.
| [MainView.java](src/main/java/com/vaadin/starter/skeleton/MainView.java) | The main view, shown when you browse for http://localhost:8080/
| [ManualJetty.java](src/main/java/com/vaadin/starter/skeleton/ManualJetty.java) | Launches the Embedded Jetty; just run the `main()` method.
| [src/main/resources/](src/main/resources) | A bunch of static files not compiled by Java in any way; see below for explanation.
| [simplelogger.properties](src/main/resources/simplelogger.properties) | Configures the logging engine; this demo uses the SLF4J logging library with slf4j-simple logger.
| [src/main/webapp/](src/main/webapp) | Static web files served as-is by the web container.
| [src/test/java/](src/test/java) | Your unit & integration tests go here.
| [MainViewTest.java](src/test/java/com/vaadin/starter/skeleton/MainViewTest.java) | Tests the Vaadin UI; uses the [Karibu-Testing](https://github.com/mvysny/karibu-testing) UI test library.
| [frontend/](frontend) | TODO
| `node_modules` | populated by `npm` - contains sources of all JavaScript web components.

## More info

For a full Vaadin application example, there are more choices available also from [vaadin.com/start](https://vaadin.com/start) page.
