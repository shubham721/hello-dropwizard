package com.shubhamgoyal.learning.hystrix.javanica.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shubhamgoyal.learning.hystrix.javanica.UnstableApi;
import com.shubhamgoyal.learning.hystrix.javanica.entities.Saying2;
import com.shubhamgoyal.learning.hystrix.javanica.services.HelloWorldService;
import rx.Observable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private HelloWorldService service;

    @Inject
    private UnstableApi api;


    @GET
    @Timed
//    @HystrixCommand(commandKey = "command2", groupKey = "command2")
    public Saying2 sayHello1(@QueryParam("name") Optional<String> name) throws InterruptedException, JsonProcessingException, ExecutionException {
        int temp = api.call(name.get());
        Integer p = api.call3(name.get());

        // Synchronous call to some api
        Integer x1 = api.call4(name.get());
        System.out.println(x1);

        // Asynchronous call to some api, returns future
        Integer temp2 = api.call2(name.get()).get();
        System.out.println(temp2);

        // Reactive execution
        Observable<Integer> x2 = api.call5(name.get());
        x2.subscribe(
                response -> {
                    System.out.println("Observable response printing");
                    System.out.println(response);
                },
                error -> System.out.println(error.getCause())
        );

        Saying2 x = new Saying2(temp);
        return x;
    }

}
