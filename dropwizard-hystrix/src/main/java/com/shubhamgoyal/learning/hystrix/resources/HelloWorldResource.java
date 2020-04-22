package com.shubhamgoyal.learning.hystrix.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shubhamgoyal.learning.hystrix.HelloWorldService;
import com.shubhamgoyal.learning.hystrix.UnstableApi;
import com.shubhamgoyal.learning.hystrix.representations.Saying2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private HelloWorldService service;


    @GET
    @Timed
    public Saying2 sayHello1(@QueryParam("name") Optional<String> name) throws InterruptedException, JsonProcessingException {
       int temp = new UnstableApi().call(name.get());
       Saying2 x = new Saying2(temp);
       return x;
    }

}