package com.shubhamgoyal.learning.hystrix.javanica.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.netflix.hystrix.contrib.javanica.aop.guice.InterceptorModule;
import com.shubhamgoyal.learning.hystrix.javanica.annotations.Profile;
import com.shubhamgoyal.learning.hystrix.javanica.interceptors.ProfileInterceptor;

public class HystrixJavanicaGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectMapper.class).toInstance(new ObjectMapper());
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Profile.class), new ProfileInterceptor());
        install(new InterceptorModule());
    }


}
