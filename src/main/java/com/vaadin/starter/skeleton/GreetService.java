package com.vaadin.starter.skeleton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GreetService {

    @NotNull
    public String greet(@Nullable String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }
}
