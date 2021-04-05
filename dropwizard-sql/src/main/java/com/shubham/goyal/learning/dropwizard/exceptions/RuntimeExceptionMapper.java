package com.shubham.goyal.learning.dropwizard.exceptions;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    public final Meter exceptions;


    public RuntimeExceptionMapper(MetricRegistry registry) {
        exceptions = registry.meter(MetricRegistry.name(getClass(), "exceptions"));
    }

    @Override
    public Response toResponse(RuntimeException e) {
        log.error("Not Found Exception: ", e);

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        return Response
                .status(status)
                .entity(e.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
