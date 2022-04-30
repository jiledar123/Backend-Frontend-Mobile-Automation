package com.main.assignment.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

/**
 * The type Rest api utilities.
 */
public class RestAPIUtilities {
    /**
     * The constant response.
     */
    public static Response response;
    /**
     * The constant request.
     */
    public static RequestSpecification request;

    /**
     * Sets base url.
     *
     * @param url the url
     */
    public void setBaseUrl(String url) {
        request = RestAssured.given();
        request.baseUri(url);
    }

    /**
     * Execute response.
     *
     * @param url        the url
     * @param methodType the method type
     * @param headerinfo the headerinfo
     * @param qParam     the q param
     * @return the response
     */
    public synchronized Response execute(String url, HttpMehodCall methodType, HashMap<String,
            String> headerinfo, HashMap<String, Object> qParam) {
        switch (methodType) {
            case GET:
                response = request.when().headers(headerinfo).queryParams(qParam).get(url);
                break;
            case PUT:
                break;
            case POST:
                break;
            case DELETE:
                break;
            default:
                System.out.println("No valid http method implementation found for found for ::" + methodType);
        }
        return response;
    }

    /**
     * Is status valid boolean.
     *
     * @param code the code
     * @return the boolean
     */
    public boolean isStatusValid(int code) {
        return response.getStatusCode() == code;
    }

}
