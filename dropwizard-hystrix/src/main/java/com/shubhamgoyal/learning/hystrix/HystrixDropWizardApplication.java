package com.shubhamgoyal.learning.hystrix;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.shubhamgoyal.learning.hystrix.config.HystrixDropwizardConfiguration;
import com.shubhamgoyal.learning.hystrix.guice.HystrixDropwizardGuiceModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HystrixDropWizardApplication  extends Application<HystrixDropwizardConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(HystrixDropWizardApplication.class);

    @Override
    public String getName() {
        return "hystrix-dropwizard-application";
    }

    @Override
    public void run(HystrixDropwizardConfiguration hystrixDropwizardConfiguration, Environment environment) throws Exception {
        log.info("In Application run");
    }

    @Override
    public void initialize(Bootstrap<HystrixDropwizardConfiguration> bootstrap) {
        log.info("In Application initialize");
//        HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixCodaHaleMetricsPublisher(bootstrap.getMetricRegistry()));

        GuiceBundle<HystrixDropwizardConfiguration> guiceBundle = GuiceBundle.
                <HystrixDropwizardConfiguration>newBuilder()
                .setConfigClass(HystrixDropwizardConfiguration.class)
                .addModule(new HystrixDropwizardGuiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .build(Stage.DEVELOPMENT);

        //Imp: Set to Stage.DEVELOPMENT to allow guice to lazy instantiate by setting Stage.Development to get around
        // hibernate dropwizard guice issue - https://github.com/HubSpot/dropwizard-guice/issues/19
        bootstrap.addBundle(guiceBundle);
    }

    public static void main(String[] args) throws Exception {
        new HystrixDropWizardApplication().run(args);
    }
}
