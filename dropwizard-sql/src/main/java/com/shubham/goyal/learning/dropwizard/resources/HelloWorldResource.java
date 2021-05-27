package com.shubham.goyal.learning.dropwizard.resources;

import com.google.inject.Inject;
import com.shubham.goyal.learning.dropwizard.dal.EmployeeDao;
import com.shubham.goyal.learning.dropwizard.dal.EmployeeJoinTableDao;
import com.shubham.goyal.learning.dropwizard.dal.entities.EmployeeEntity;
import com.shubham.goyal.learning.dropwizard.dal.entities.EmployeeEntityJoinTable;
import com.shubham.goyal.learning.dropwizard.entities.Student;
import com.shubham.goyal.learning.dropwizard.services.interfaces.HelloWorldService;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "Sample hello dropwizard app")
@Slf4j
public class HelloWorldResource {

    private HelloWorldService service;

    private EmployeeDao employeeDao;

    private EmployeeJoinTableDao employeeJoinTableDao;


    @Inject
    public HelloWorldResource(
            HelloWorldService service,
            EmployeeDao employeeDao,
            EmployeeJoinTableDao employeeJoinTableDao){
        this.service = service;
        this.employeeDao = employeeDao;
        this.employeeJoinTableDao = employeeJoinTableDao;
    }

    @Path("/student")
    @POST
    @ApiOperation(value = "Register Student", httpMethod = "POST")
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

    @GET
    @ApiOperation(value = "Fetch Student By Name and phone", httpMethod = "GET")
    @Path("/student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found Student"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @UnitOfWork
    public Response fetchStudent(
            @QueryParam("name") String name,
            @QueryParam("phone") String phone) throws Exception {

        Student student = service.fetchStudent(name, phone);

        return Response.status(Response.Status.OK).entity(student).build();
    }

    @Path("/employee")
    @POST
    @ApiOperation(value = "Register Employee", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Register Employee successful"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @UnitOfWork
    public Response registerEmployee(
            EmployeeEntityJoinTable employee) {


        EmployeeEntityJoinTable employeeEntity = employeeJoinTableDao.save(employee);

        return Response.status(Response.Status.CREATED).entity(employeeEntity).build();
    }

    @Path("/employee/{id}")
    @GET
    @ApiOperation(value = "GET Employee", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "GET Employee successful"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @UnitOfWork
    public Response fetchEmployee(
            @HeaderParam("id") int id) {


        EmployeeEntityJoinTable employeeEntity = employeeJoinTableDao.getEntity(id);

        return Response.status(Response.Status.OK).entity(employeeEntity).build();
    }

}
