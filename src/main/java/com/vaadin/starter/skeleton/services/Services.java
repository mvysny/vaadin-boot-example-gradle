package com.vaadin.starter.skeleton.services;

import org.jetbrains.annotations.NotNull;

/**
 * Provides all available services. See <a href="https://github.com/mvysny/vaadin-boot#services">Vaadin Boot Services</a>
 * for more information on the pattern.
 */
public class Services {
    @NotNull
    public static GreetService getGreetService() {
        return new GreetService();
    }
}
