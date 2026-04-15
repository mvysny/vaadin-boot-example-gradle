# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

A demo Vaadin Flow app that runs from an embedded Jetty via a plain `main()` method using [Vaadin Boot](https://github.com/mvysny/vaadin-boot). Java 21+ required. Versions are pinned in `gradle/libs.versions.toml`.

## Common commands

All commands use the Gradle wrapper (`./gradlew`).

- Run in dev mode: `./gradlew run` — starts embedded Jetty on http://localhost:8080. Vaadin runs in dev mode (frontend rebuilt on the fly via Vite).
- Build + test (development mode): `./gradlew build` (or just `./gradlew`, since `defaultTasks` is `clean build`).
- Production build: `./gradlew clean build -Pvaadin.productionMode` — produces `build/distributions/app.zip` containing a runner script and all jars. The `vaadin-dev` dep is omitted in production mode (see `build.gradle.kts:18-20`).
- Run tests: `./gradlew test`.
- Run a single test: `./gradlew test --tests com.vaadin.starter.skeleton.MainViewTest.testGreeting`.
- Run the packaged production app: unzip `build/distributions/app.zip` then run `app/bin/app`.
- Docker: `docker build -t test/vaadin-boot-example-gradle:latest .` then `docker run --rm -ti -p8080:8080 test/vaadin-boot-example-gradle` — the Dockerfile uses a multi-stage build that runs the production-mode gradle build inside the builder stage.

## Architecture

Three load-bearing pieces, all small:

1. **`Main`** (`src/main/java/.../Main.java`) — entry point. Just calls `new VaadinBoot().run()`. There is no Spring, no servlet container config, no `web.xml`. Vaadin Boot wires up an embedded Jetty and discovers `@Route` classes via classpath scanning.
2. **`AppShell`** — implements `AppShellConfigurator` and carries app-wide annotations (`@PWA`, `@StyleSheet`). This is where global stylesheets/themes are registered; per-Vaadin-Flow rules, exactly one `AppShellConfigurator` may exist.
3. **`Services`** — a plain static service-locator (`Services.getGreetService()`). The pattern is documented at https://github.com/mvysny/vaadin-boot#services. There is no DI framework; views call `Services.getXxx()` directly. When adding new services, follow the same pattern rather than introducing Spring/Guice.

`@Route("")` on `MainView` is what binds the root URL to the view; route discovery is automatic.

## Testing

Tests use [Karibu-Testing](https://github.com/mvysny/karibu-testing) for browserless Vaadin testing — no Jetty, no real browser. The pattern (see `MainViewTest.java`):

- `Routes().autoDiscoverViews(...)` is run **once** in `@BeforeAll` (classpath scan is expensive); the result is cached in a static field.
- `MockVaadin.setup(routes)` in `@BeforeEach` creates a fresh `UI`/`VaadinSession` per test and navigates to `/`.
- `MockVaadin.tearDown()` in `@AfterEach`.
- Use `_get(...)`, `_click(...)`, `_setValue(...)`, `expectNotifications(...)` from `LocatorJ` / `NotificationsKt` instead of walking the component tree manually.

When adding a test, mirror this lifecycle — do **not** start Jetty from tests.

## Logging

SLF4J → `slf4j-simple`. Configuration lives in `src/main/resources/simplelogger.properties`. Jetty/Atmosphere are quieted there; if you add a chatty dependency, raise its threshold in that file rather than changing the default level.

## CI

`.github/workflows/gradle.yml` builds against Java 21 and 25 with `-Pvaadin.productionMode`. If a change builds locally in dev mode but you haven't tried prod mode, run `./gradlew clean build -Pvaadin.productionMode` before pushing — that's what CI runs.
