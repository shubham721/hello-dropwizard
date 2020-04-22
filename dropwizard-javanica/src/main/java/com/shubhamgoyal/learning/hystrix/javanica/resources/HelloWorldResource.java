package com.shubhamgoyal.learning.hystrix.javanica.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.shubhamgoyal.learning.hystrix.javanica.UnstableApi;
import com.shubhamgoyal.learning.hystrix.javanica.entities.Saying2;
import com.shubhamgoyal.learning.hystrix.javanica.services.HelloWorldService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

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
    public Saying2 sayHello1(@QueryParam("name") Optional<String> name) throws InterruptedException, JsonProcessingException {
        int temp = api.call(name.get());
        api.call2(name.get());
        Saying2 x = new Saying2(temp);
        return x;
    }

}
