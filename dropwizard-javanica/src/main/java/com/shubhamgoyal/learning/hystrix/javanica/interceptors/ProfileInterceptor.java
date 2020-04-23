package com.shubhamgoyal.learning.hystrix.javanica.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ProfileInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object response = invocation.proceed();
        System.out.println("invoked" + invocation.getMethod().getName());
        return response;
    }
}
