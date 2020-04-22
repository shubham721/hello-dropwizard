package com.shubhamgoyal.learning.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shubhamgoyal.learning.hystrix.annotations.Profile;

public class UnstableApi {

    public UnstableApi(){

    }

    @Profile
    public int call(String param) throws InterruptedException {
        Thread.sleep(2000);
        return param.length();
    }
}
