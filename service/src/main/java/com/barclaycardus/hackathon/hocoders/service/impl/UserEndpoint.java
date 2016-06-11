package com.barclaycardus.hackathon.hocoders.service.impl;

import com.barclaycardus.hackathon.hocoders.service.dto.User;
import com.barclaycardus.hackathon.hocoders.service.rds.RDSDataAccessObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by abhishek on 11/06/16.
 */
@Component
@Path("user")
public class UserEndpoint {


    @Inject
    RDSDataAccessObject<User> userDataAccessObject;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<User>> getUsers() throws Exception {
        List<User> users = userDataAccessObject.read();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<User> addUser(User user) throws Exception{
        userDataAccessObject.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<User> editUser(User user) throws Exception {
        userDataAccessObject.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<User> deleteUser(User user) throws Exception {
        userDataAccessObject.delete(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


}
