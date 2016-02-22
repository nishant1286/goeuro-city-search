package com.goeuro.search.api.response;

import com.goeuro.search.api.response.exception.ResultProcessingException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by nishantgupta on 22/2/16.
 */
public interface ResponseProcessor<T> {
    public void process(String query, Collection<T> results) throws ResultProcessingException;
}
