package com.barclaycardus.hackathon.hocoders.service.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by abhishek on 11/06/16.
 */
public class CustomException extends WebApplicationException {

    public CustomException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(message).build());
    }

}
