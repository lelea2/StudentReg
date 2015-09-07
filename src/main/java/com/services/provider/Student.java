package com.services.provider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "student" path)
 */

@Path("student")

public class Student {

    /**
     * Method handling HTTP PUT requests for register user
     * The returned is statusCode 200
     */
    @PUT
    @Path("/register/{studentId}")
    public void registerStudent() {
    }
}
