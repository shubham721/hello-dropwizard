package com.shubhamgoyal.learning.hystrix.javanica;

import com.google.inject.Inject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shubhamgoyal.learning.hystrix.javanica.annotations.Profile;

import java.util.Properties;

public class UnstableApi {

    private Properties properties;

    @Inject
    public UnstableApi(Properties properties){
        this.properties = properties;
    }

    @Profile
    public int call(String param) throws InterruptedException {
//        Thread.sleep(2000);
        return param.length();
    }

    @HystrixCommand(commandKey = "simple-command", groupKey = "simple-command")
    public  Integer call2(String param) {
        return param.length();
    }


    private Integer fallback(String param) {
        return 5;
    }
}
