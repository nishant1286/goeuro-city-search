package com.goeuro.search.api.request;

import com.goeuro.search.api.request.exception.RequestProcessingException;

import java.util.Collection;
/**
 * Created by nishantgupta on 22/2/16.
 */
public interface RequestProcessor<T> {
    public Collection<T> process(String query) throws RequestProcessingException;
}
