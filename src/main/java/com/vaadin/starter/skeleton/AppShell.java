package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
@Theme("my-theme")
public class AppShell implements AppShellConfigurator {
}
