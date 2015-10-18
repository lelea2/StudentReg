package com.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Start init for Jersey apps running
 */
public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        register(JacksonFeature.class);
        register(Courses.class);
        register(Majors.class);
        register(Schedules.class);
        register(Users.class);
        register(Users_Courses.class);
    }
}