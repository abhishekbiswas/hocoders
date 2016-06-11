package com.barclaycardus.hackathon.hocoders.service.impl;

import com.barclaycardus.hackathon.hocoders.service.config.ServiceConfigHolder;
import com.barclaycardus.hackathon.hocoders.service.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by abhishek on 11/06/16.
 */
@Component
@Path("myresource")
public class MyResource {

    @Inject
    ServiceConfigHolder serviceConfigHolder;

    private static final Logger log = LoggerFactory.getLogger(MyResource.class);

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        log.info("info - request received!");
        log.debug("debug - request received");
        log.info("info - request processed!");
        log.debug("debug - request processed");
        return "Got it!";
    }

    @GET
    @Path("getit")
    @Produces("application/json")
    public ResponseEntity<String> getItJson() {
        return new ResponseEntity<>("Returning in JSON", HttpStatus.OK);
    }

    @GET
    @Path("greeting")
    @Produces(MediaType.TEXT_PLAIN)
    public String greet(@DefaultValue("Guest") @QueryParam("name") String name) {
        if(name.matches("^[A-Z][a-zA-Z]+$")){
            return ("Hello " + name + "!");
        }
        throw new CustomException("not a valid name!");
    }

    @GET
    @Path("fault")
    @Produces(MediaType.TEXT_PLAIN)
    public String throwException() throws Exception {
        throw new Exception("stop bugging me!");
    }

    @GET
    @Path("environment")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEnvironmentMessage(@QueryParam("env") String env) {
        return ("Environment: " + serviceConfigHolder.getMessageForService(env));
    }

}

