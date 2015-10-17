package com.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class Users {

    @Autowired
    private UserDAO userDAO;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Users> getAllUser () {
        ArrayList<Users> userList = new ArrayList<Users>();
        try {
            userList = userDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

}