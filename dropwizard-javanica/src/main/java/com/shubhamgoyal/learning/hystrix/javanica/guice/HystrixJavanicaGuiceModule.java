package com.shubhamgoyal.learning.hystrix.javanica.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.netflix.hystrix.contrib.javanica.aop.guice.InterceptorModule;
import com.shubhamgoyal.learning.hystrix.javanica.annotations.Profile;
import com.shubhamgoyal.learning.hystrix.javanica.config.HystrixJavanicaConfiguration;
import com.shubhamgoyal.learning.hystrix.javanica.interceptors.ProfileInterceptor;

public class HystrixJavanicaGuiceModule extends AbstractModule {

    private HystrixJavanicaConfiguration configuration;

    public HystrixJavanicaGuiceModule(HystrixJavanicaConfiguration configuration)
    {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(ObjectMapper.class).toInstance(new ObjectMapper());
        install(new HystrixModule(configuration.getHystrixConfig()));
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Profile.class), new ProfileInterceptor());
        install(new InterceptorModule());
    }


}
