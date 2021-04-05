package com.shubham.goyal.learning.dropwizard;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.shubham.goyal.learning.dropwizard.config.HelloWorldConfiguration;
import com.shubham.goyal.learning.dropwizard.exceptions.RuntimeExceptionMapper;
import com.shubham.goyal.learning.dropwizard.guice.HelloWorldGuiceModule;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    private Injector injector;

    private final SwaggerBundle<HelloWorldConfiguration> swaggerBundle = new SwaggerBundle<HelloWorldConfiguration>() {
        @Override
        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(HelloWorldConfiguration config) {
            return config.getSwagger();
        }
    };

    private final MigrationsBundle<HelloWorldConfiguration> migrationsBundle = new MigrationsBundle<HelloWorldConfiguration>() {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
            return configuration.getDatabase();
        }
    };


    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        super.initialize(bootstrap);
        log.info("In Application initialize");
        //HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixCodaHaleMetricsPublisher(bootstrap.getMetricRegistry()));

        // Adding different bundles in dropwizard guice configuration.
        GuiceBundle<HelloWorldConfiguration> guiceBundle = GuiceBundle
                .<HelloWorldConfiguration>newBuilder()
                .setConfigClass(HelloWorldConfiguration.class)
                .addModule(new HelloWorldGuiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .build(Stage.DEVELOPMENT);

        bootstrap.addBundle(swaggerBundle);
        bootstrap.addBundle(guiceBundle);
        injector = guiceBundle.getInjector();
        DI.setINJECTOR(injector);
            bootstrap.addBundle(
                    injector.getInstance(Key.get(new TypeLiteral<HibernateBundle<HelloWorldConfiguration>>() {
                    })));
        bootstrap.addBundle(migrationsBundle);
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration,
                    Environment environment) throws Exception {
        log.info("In Application run");
        environment.jersey().register(new RuntimeExceptionMapper(environment.metrics()));
    }

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }
}
