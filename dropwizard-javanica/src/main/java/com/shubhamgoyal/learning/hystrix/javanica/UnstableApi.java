package com.shubhamgoyal.learning.hystrix.javanica;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shubhamgoyal.learning.hystrix.javanica.annotations.Profile;

public class UnstableApi {

    public UnstableApi(){

    }

    @Profile
    public int call(String param) throws InterruptedException {
//        Thread.sleep(2000);
        return param.length();
    }

    @HystrixCommand(
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2")})
    public  Integer call2(String param) throws InterruptedException{
        return param.length();
    }
}
