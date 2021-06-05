package com.godel.mastery.constant;

/**
 * Defines messages for exceptions.
 */
public class ExceptionMessage {

    public static String getResourceNotFoundMessage(int id){
        return "Requested resource not found (id = " + id + ")";
    }
}
