package com.goeuro.search.api.response.exception;

/**
 * Created by nishantgupta on 22/2/16.
 */
public class ResultProcessingException extends Exception {
    public ResultProcessingException(String msg, Throwable e){
        super(msg, e);
    }
}

