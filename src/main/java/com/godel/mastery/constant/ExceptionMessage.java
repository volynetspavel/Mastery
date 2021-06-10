package com.godel.mastery.constant;

/**
 * Defines messages for exceptions.
 */
public class ExceptionMessage {

    public static final String DATE_FORMAT_EXCEPTION_MESSAGE = "Incorrect format of date, date must be according 'yyyy-MM-dd'.";
    public static final String INCORRECT_PARAM_OF_REQUEST_EXCEPTION_MESSAGE = "Incorrect parameter of request.";

    public static String getResourceNotFoundMessage(int id){
        return "Requested resource not found (id = " + id + ")";
    }
}
