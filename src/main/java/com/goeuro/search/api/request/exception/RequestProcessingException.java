package com.goeuro.search.api.request.exception;

/**
 * Created by nishantgupta on 22/2/16.
 */
public class RequestProcessingException extends Exception {
    public RequestProcessingException(String msg, Throwable e){
        super(msg, e);
    }
}

