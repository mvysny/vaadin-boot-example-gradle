package com.vaadin.starter.skeleton;

import com.github.mvysny.vaadinboot.VaadinBoot;
import org.jetbrains.annotations.NotNull;

/**
 * Run {@link #main(String[])} to launch your app in Embedded Jetty.
 * @author mavi
 */
public final class Main {
    public static void main(@NotNull String[] args) throws Exception {
        new VaadinBoot().withArgs(args).run();
    }
}
