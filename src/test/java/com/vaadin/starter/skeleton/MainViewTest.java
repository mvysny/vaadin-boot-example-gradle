package com.vaadin.starter.skeleton;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._click;
import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static com.github.mvysny.kaributesting.v10.NotificationsKt.expectNotifications;

/**
 * In this app we employ the browserless testing technique, which allows for very quick and easy test run.
 * Please see [Karibu-Testing](https://github.com/mvysny/karibu-testing) for a list of advantages of
 * browserless tests.
 *
 * Makes sure that the app is "up" (initialized in this JVM) and Vaadin
 * is properly mocked, so that we can navigate around the app and poke the UI.
 *
 * Since we already have a JVM (the one which runs these tests), Vaadin is mocked and
 * we will not use an actual browser for testing, we do not need to actually start Jetty.
 * @author mavi
 */
public class MainViewTest {
    private static Routes routes;

    @BeforeAll
    public static void createRoutes() {
        // initialize routes only once, to avoid view auto-detection before every test and to speed up the tests
        routes = new Routes().autoDiscoverViews("com.vaadin.starter.skeleton");
    }

    @BeforeEach
    public void setupVaadin() {
        MockVaadin.setup(routes);
    }

    @AfterEach
    public void teardownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void clickButton() {
        // open the Main View
        UI.getCurrent().navigate(MainView.class);

        // simulate a button click as if clicked by the user
        _click(_get(Button.class, spec -> spec.withCaption("Click me")));

        // check that the notification has been shown
        expectNotifications("Clicked!");
    }
}
