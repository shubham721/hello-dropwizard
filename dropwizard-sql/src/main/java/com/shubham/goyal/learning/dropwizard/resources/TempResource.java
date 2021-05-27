package com.shubham.goyal.learning.dropwizard.resources;


import com.google.inject.Inject;
import com.shubham.goyal.learning.dropwizard.dal.EmployeeDao;
import com.shubham.goyal.learning.dropwizard.dal.EmployeeJoinTableDao;
import com.shubham.goyal.learning.dropwizard.entities.Student;
import com.shubham.goyal.learning.dropwizard.services.interfaces.HelloWorldService;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "Sample hello Temp app")
@Slf4j
public class TempResource {

    private HelloWorldService service;

    private EmployeeDao employeeDao;

    private EmployeeJoinTableDao employeeJoinTableDao;


    @Inject
    public TempResource(
            HelloWorldService service,
            EmployeeDao employeeDao,
            EmployeeJoinTableDao employeeJoinTableDao){
        this.service = service;
        this.employeeDao = employeeDao;
        this.employeeJoinTableDao = employeeJoinTableDao;
    }

    @Path("/temp")
    @POST
    @ApiOperation(value = "Register Temp", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Register Student successful"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @UnitOfWork
    public Response registerStudent(
            Student student) {


        int id = service.registerStudent(student);

        return Response.status(Response.Status.CREATED).entity(id).build();
    }
}
