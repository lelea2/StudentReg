package com.util.response;

import java.util.*;
import javax.ws.rs.core.Response;
import com.util.constant.Constant;

/**
 * Generate common response
 */
public class ComponentResponse {

    /**
     * Function return ok status with response {status: 'SUCCESS'}
     * @return
     */
    public static Response okResponse() {
        return Response.status(Response.Status.OK).entity(Constant.Status.SUCCESS).build();
    }

    /**
     * Function return ok response 200 with object as array data
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Response okResponse(List<T> data) {
        return Response.status(Response.Status.OK).entity(generateCommonResponse(data)).build();
    }

    /**
     * Function return ok response with object data
     * @param data
     * @return
     */
    public static Response okResponse(Object data) {
        return Response.status(Response.Status.OK).entity(generateCommonResponse(data)).build();
    }

    /**
     * Function return 201 response with status: "SUCCESS"
     * @return
     */
    public static Response createdResponse() {
        return Response.status(Response.Status.CREATED).entity(Constant.Status.SUCCESS).build();
    }

    /**
     * Function return 201 with response contain data obj
     * @param data
     * @return
     */
    public static Response createdResponse(Object data) {
        return Response.status(Response.Status.CREATED).entity(generateCommonResponse(data)).build();
    }

    /**
     * Function return error response
     * @return
     */
    public static Response errorResponse() {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Constant.Status.FAIL).build();
    }

    /**
     * Helper function generate response object in standardize format
     * @param data
     * @param <T>
     * @return
     */
    private static<T> HashMap<String, List<T>> generateCommonResponse(List<T> data) {
        HashMap<String, List<T>> resp = new HashMap<String, List<T>>();
        resp.put("data", data);
        return resp;
    }

    /**
     * Helper function generate response object in standardize format
     * @param data
     * @param <T>
     * @return
     */
    private static<T> HashMap<String, T> generateCommonResponse(T data) {
        HashMap<String, T> resp = new HashMap<String, T>();
        resp.put("data", data);
        return resp;
    }
}

