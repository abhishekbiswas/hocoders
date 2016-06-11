package com.barclaycardus.hackathon.hocoders.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by abhishek on 11/06/16.
 */
@Provider
public class JerseyExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger log = LoggerFactory.getLogger(JerseyExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        int responseErrorCode = getResponseErrorCode(exception);
        log.warn("Http Status Code: [{}]", responseErrorCode, exception);
        return Response.status(responseErrorCode).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
    }

    public int getResponseErrorCode(Throwable exception) {
        if (exception instanceof Exception) {
            return Response.Status.BAD_REQUEST.getStatusCode();
        }
        return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }

}
