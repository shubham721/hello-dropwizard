package com.shubhamgoyal.learning.hystrix.javanica;

import com.netflix.hystrix.contrib.javanica.aop.AopType;
import com.netflix.hystrix.contrib.javanica.aop.WeavingMode;
import com.shubhamgoyal.learning.hystrix.javanica.config.HystrixJavanicaConfiguration;
import com.shubhamgoyal.learning.hystrix.javanica.guice.HystrixJavanicaGuiceModule;
import com.shubhamgoyal.learning.hystrix.javanica.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JavanicaBootstrap extends Application<HystrixJavanicaConfiguration> {

    @Override
    public void run(HystrixJavanicaConfiguration configuration, Environment environment) throws Exception {
        System.setProperty("aopType", AopType.GUICE.name());
        System.setProperty("weavingMode", WeavingMode.RUNTIME.name());
        DI.install(new HystrixJavanicaGuiceModule(configuration));
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
