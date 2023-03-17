package com.vaadin.starter.skeleton;

import com.github.mvysny.vaadinboot.VaadinBoot;
import org.eclipse.jetty.quickstart.QuickStartConfiguration;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Run {@link #main(String[])} to launch your app in Embedded Jetty.
 * @author mavi
 */
public final class Main {
    public static void main(@NotNull String[] args) throws Exception {
        final boolean dumpQuickstartWeb = false;
        final VaadinBoot boot = new VaadinBoot() {
            protected WebAppContext createWebAppContext() throws IOException {
                WebAppContext ctx = super.createWebAppContext();
                if (dumpQuickstartWeb) {
                    ctx.setAttribute(QuickStartConfiguration.MODE, QuickStartConfiguration.Mode.GENERATE);
                    final File quickstartWeb = new File("quickstart-web.xml").getAbsoluteFile();
                    System.out.println("Quickstart will be generated to " + quickstartWeb);
                    ctx.setAttribute(QuickStartConfiguration.QUICKSTART_WEB_XML, new PathResource(quickstartWeb));
                } else {
                    ctx.setAttribute(QuickStartConfiguration.MODE, QuickStartConfiguration.Mode.QUICKSTART);
                    final Resource quickstartXml = Objects.requireNonNull(Resource.newClassPathResource("/webapp/WEB-INF/quickstart-web.xml"));
                    ctx.setAttribute(QuickStartConfiguration.QUICKSTART_WEB_XML, quickstartXml);
                }
                return ctx;
            }
        }.withArgs(args);
        if (!dumpQuickstartWeb) {
            boot.disableClasspathScanning();
        }
        boot.run();
    }
}
