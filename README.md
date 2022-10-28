[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vaadin-flow/Lobby#?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/mvysny/vaadin14-embedded-jetty-gradle)

# Vaadin 23 running in Embedded Jetty using Gradle

A demo project showing the possibility of running a Vaadin app from an
embedded Jetty, as a simple `main()` method. Written in Java.
Uses [Vaadin Boot](https://github.com/mvysny/vaadin-boot).

Both the development and production modes are supported. Also, the project
demoes packaging itself into a zip file containing
a list of jars and a runner script. See "Packaging for production" below
for more details.

> Looking for **Vaadin 14 Gradle** version? Please see [vaadin14-embedded-jetty-gradle](https://github.com/mvysny/vaadin14-embedded-jetty-gradle)

> Looking for **Vaadin 23 Maven** version? Please see [vaadin-embedded-jetty](https://github.com/mvysny/vaadin-embedded-jetty)

> **Note:** This example project launches Jetty itself and therefore doesn't use the Gretty Gradle plugin.
If you wish to use the Gretty plugin and build a WAR archive, then please see
[karibu10-helloworld-app](https://github.com/mvysny/karibu10-helloworld-application) instead.

# Documentation

Please see the [Vaadin Boot](https://github.com/mvysny/vaadin-boot) documentation
on how you run, develop and package this Vaadin-Boot-based app.
