package com.vaadin.starter.skeleton;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ.*;
import static com.github.mvysny.kaributesting.v10.NotificationsKt.expectNotifications;

/**
 * In this app we employ the browserless testing technique, which allows for very quick and easy test run.
 * Please see <a href="https://github.com/mvysny/karibu-testing">Karibu-Testing</a> for a list of advantages of
 * browserless tests.
 * <p></p>
 * Makes sure that the app is "up" (initialized in this JVM) and Vaadin
 * is properly mocked, so that we can navigate around the app and poke the UI.
 * <p></p>
 * Since we already have a JVM (the one which runs these tests), Vaadin is mocked and
 * we will not use an actual browser for testing, we do not need to actually start Jetty.
 * @author mavi
 */
public class MainViewTest {
    private static Routes routes;

    @BeforeAll
    public static void createRoutes() {
        // Route discovery involves classpath scanning and is an expensive operation.
        // Running the discovery process only once per test run speeds up the test runtime considerably.
        // Discover the routes once and cache the result.
        routes = new Routes().autoDiscoverViews("com.vaadin.starter.skeleton");
    }

    @BeforeEach
    public void setupVaadin() {
        // MockVaadin.setup() registers all @Routes, prepares the Vaadin instances for us
        // (the UI, the VaadinSession, VaadinRequest, VaadinResponse, ...) and navigates to the root route.
        MockVaadin.setup(routes);
    }

    @AfterEach
    public void teardownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void smokeTest() {
        // Smoke test is a quick test to check that the basic machinery is in place and works.
        // The analogy would be to turn on an electric device (e.g. a coffee maker)
        // then turn it off immediately without even checking whether it actually works or not,
        // and watch whether there is any smoke. If yes, the coffee maker is
        // probably burning from a short-circuit and any further tests are pointless.

        // The root route should be set directly in the UI; let's check whether it is so.
        // This demoes the direct access to the UI and its children and grand-children,
        // which encompasses all visible Vaadin components.
        MainView main = (MainView) UI.getCurrent().getChildren().findFirst().get();

        // However when using this kind of low-level lookups, the code quickly gets
        // pretty complicated. Let's use the _get() function instead,
        // which will walk the UI tree for us.
        _assertOne(MainView.class);
    }

    @Test
    public void testGreeting() {
        // simulate an user input
        _setValue(_get(TextField.class, spec -> spec.withCaption("Your name")), "Martin");

        // simulate a button click as if clicked by the user
        _click(_get(Button.class, spec -> spec.withCaption("Say hello")));

        // check that the notification has been shown
        expectNotifications("Hello Martin");
    }
}
