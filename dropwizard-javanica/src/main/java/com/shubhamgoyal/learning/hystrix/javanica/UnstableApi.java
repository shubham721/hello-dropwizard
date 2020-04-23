package com.shubhamgoyal.learning.hystrix.javanica;

import com.google.inject.Inject;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;
import com.shubhamgoyal.learning.hystrix.javanica.annotations.Profile;
import lombok.SneakyThrows;
import rx.Observable;

import java.util.Properties;
import java.util.concurrent.Future;

public class UnstableApi {

    private Properties properties;

    @Inject
    public UnstableApi(Properties properties){
        this.properties = properties;
    }

    // checking how interceptors work
    @Profile
    public int call(String param) throws InterruptedException {
//        Thread.sleep(2000);
        return param.length();
    }

    // Asynchronous mode
    @HystrixCommand(commandKey = "simple-command", groupKey = "simple-command")
    public Future<Integer> call2(String param) {
        return new AsyncResult<Integer>() {
            @SneakyThrows
            @Override
            public Integer invoke() {
                Thread.sleep(110);
                return param.length();
            }
        };
    }

    //Reactive mode
    @HystrixCommand(commandKey = "command3", groupKey = "command3")
    public Observable<Integer> call5(String param) {
        return new ObservableResult<Integer>() {
            @SneakyThrows
            @Override
            public Integer invoke() {
                Thread.sleep(110);
                return param.length();
            }
        };
    }

    // Synchronous mode
    @SneakyThrows
    @HystrixCommand(commandKey = "command2", groupKey = "command2")
    public Integer call4(String param) {
        Thread.sleep(100);
      return param.length();
    }



    public Integer call3(String param){
        com.netflix.hystrix.HystrixCommand.Setter setter = com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("abc")).
                andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withCircuitBreakerErrorThresholdPercentage(20).withExecutionTimeoutInMilliseconds(200)
        );
        com.netflix.hystrix.HystrixCommand command = new com.netflix.hystrix.HystrixCommand<Integer>(setter) {
            @Override
            protected Integer run() throws Exception {
                Thread.sleep(150);
                System.out.println("Hello");
                return param.length();
            }
        };
        return (Integer) command.execute();
    }


    private Integer fallback(String param) {
        return 5;
    }
}
