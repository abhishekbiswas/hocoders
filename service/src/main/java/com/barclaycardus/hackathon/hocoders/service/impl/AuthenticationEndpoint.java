package com.barclaycardus.hackathon.hocoders.service.impl;

import com.barclaycardus.hackathon.hocoders.service.dto.User;
import com.barclaycardus.hackathon.hocoders.service.rds.RDSDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by abhishek on 12/06/16.
 */

@Component
@Path("authentication")
public class AuthenticationEndpoint {

    @Autowired
    RDSDataAccessObject<User> userDataAccessObject;

    @GET
    public ResponseEntity<String> authenticate(@QueryParam("username") String username, @QueryParam("password") String password) throws Exception {
        List<User> userList = userDataAccessObject.query("findAllUsersByUserId", "userId", username);

        ResponseEntity<String> response;
        if(userList.size() == 1 && userList.get(0).getPassword().equals(password)) {
            response = new ResponseEntity<String>("User Successfully Authenticated!", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>("User Not Found!", HttpStatus.FORBIDDEN);
        }

        return response;
    }

}
