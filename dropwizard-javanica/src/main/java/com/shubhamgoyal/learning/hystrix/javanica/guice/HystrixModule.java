package com.shubhamgoyal.learning.hystrix.javanica.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.netflix.config.ConfigurationManager;
import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class HystrixModule extends AbstractModule {

    private static final Logger flogger = LoggerFactory.getLogger(HystrixModule.class);

    private String hystrixConfig;

    public HystrixModule(String hystrixConfig) {
        this.hystrixConfig = hystrixConfig;
    }

    @Override
    protected void configure() {
        /*
         * no state to configure
         * */
    }

    @Provides
    public Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(hystrixConfig));
            AbstractConfiguration configInstance = ConfigurationManager.getConfigInstance();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                configInstance.setProperty(entry.getKey().toString(), entry.getValue());
            }
        } catch (IOException e) {
            flogger.error("Unable to read config file " + hystrixConfig);
        }
        return properties;
    }
}