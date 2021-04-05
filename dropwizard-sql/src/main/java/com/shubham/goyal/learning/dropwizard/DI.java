package com.shubham.goyal.learning.dropwizard;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DI {
    private static final Logger logger = LoggerFactory.getLogger(DI.class);
    private static Injector INJECTOR = Guice.createInjector(new Module[0]);

    public DI() {
    }

    public static void install(Module module) {
        Class var1 = DI.class;
        synchronized(DI.class) {
            logger.info("Installing DI module: " + module.getClass().getName());
            INJECTOR = Guice.createInjector(new Module[]{module});
            logger.info("Installed DI module: " + module.getClass().getName());
        }
    }

    public static void setINJECTOR(Injector injector) {
        INJECTOR = injector;
    }

    public static Injector di() {
        return INJECTOR;
    }
}
