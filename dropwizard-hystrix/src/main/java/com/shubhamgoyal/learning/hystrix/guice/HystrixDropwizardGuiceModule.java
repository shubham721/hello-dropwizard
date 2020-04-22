package com.shubhamgoyal.learning.hystrix.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.netflix.hystrix.contrib.javanica.aop.guice.InterceptorModule;
import com.shubhamgoyal.learning.hystrix.annotations.Profile;
import com.shubhamgoyal.learning.hystrix.interceptors.ProfileInterceptor;

public class HystrixDropwizardGuiceModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(ObjectMapper.class).toInstance(new ObjectMapper());
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Profile.class), new ProfileInterceptor());
        install(new InterceptorModule());
        bindConstant().annotatedWith(Names.named("port")).to(42);
    }

}
