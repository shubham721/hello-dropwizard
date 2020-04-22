package com.shubhamgoyal.learning.hystrix.javanica;

import com.shubhamgoyal.learning.hystrix.javanica.config.HystrixJavanicaConfiguration;
import com.shubhamgoyal.learning.hystrix.javanica.guice.HystrixJavanicaGuiceModule;
import com.shubhamgoyal.learning.hystrix.javanica.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JavanicaBootstrap extends Application<HystrixJavanicaConfiguration> {

    @Override
    public void run(HystrixJavanicaConfiguration configuration, Environment environment) throws Exception {
        DI.install(new HystrixJavanicaGuiceModule());
        environment.jersey().register(getHelloWorldResource());
    }

    private HelloWorldResource getHelloWorldResource(){
        return DI.di().getInstance(HelloWorldResource.class);
    }


    @Override
    public void initialize(Bootstrap<HystrixJavanicaConfiguration> bootstrap) {

    }

    @Override
    public String getName() {
        return "hystrix-javanica-poc";
    }


    public static void main(String[] args) throws Exception {
        new JavanicaBootstrap().run(args);
    }


}
