[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vaadin-flow/Lobby#?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/mvysny/vaadin-boot-example-gradle)

# Vaadin Boot Example App using Gradle

A demo project showing the possibility of running a Vaadin app from an
embedded Jetty, as a simple `main()` method. Written in Java.
Uses [Vaadin Boot](https://github.com/mvysny/vaadin-boot). Requires Java 17+.

Both the development and production modes are supported. Also, the project
demoes packaging itself into a zip file containing
a list of jars and a runner script. See "Packaging for production" below
for more details.

> Looking for **Vaadin 14 Gradle** version? Please see [vaadin14-boot-example-gradle](https://github.com/mvysny/vaadin14-boot-example-gradle)

> Looking for **Vaadin 24 Maven** version? Please see [vaadin-boot-example-maven](https://github.com/mvysny/vaadin-boot-example-maven)

> Looking for **Vaadin 23 Gradle** version? Please see the [v23 branch](tree/v23).

See the live demo at [v-herd.eu/vaadin-boot-example-gradle/](https://v-herd.eu/vaadin-boot-example-gradle/)

# Documentation

Please see the [Vaadin Boot](https://github.com/mvysny/vaadin-boot#preparing-environment) documentation
on how you run, develop and package this Vaadin-Boot-based app.

# Native Mode

Alpha quality. Demoes a preliminary support for [GraalVM](https://www.graalvm.org/) native mode.
At the moment doesn't work: [Jetty #9514](https://github.com/eclipse/jetty.project/issues/9514).
See [Vaadin Boot #10](https://github.com/mvysny/vaadin-boot/issues/10) and [Vaadin Boot: Native](https://github.com/mvysny/vaadin-boot#native)
for more details.

Quick steps:

1. Install GraalVM as per https://graalvm.github.io/native-build-tools/latest/gradle-plugin-quickstart.html
2. `git clone https://github.com/mvysny/vaadin-boot-example-gradle`
3. `git checkout native`
4. `./gradlew clean build nativeCompile -Pvaadin.productionMode`
5. `cd build/native/nativeCompile/`
6. Run the `vaadin-boot-example-gradle` binary
