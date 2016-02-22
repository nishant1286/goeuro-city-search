package com.goeuro.search.api.request;

import com.goeuro.search.api.request.exception.RequestProcessingException;
import com.goeuro.search.api.response.exception.ResultProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by nishantgupta on 22/2/16.
 */
@Slf4j
public abstract class ExternalAPIRequestProcessor<T> implements RequestProcessor<T> {

    private final String externalEndPoint;
    private final long connectionTimeout;
    private final long socketTimeout;

    /**
     * http://stackoverflow.com/a/10380856
     */
    private static final Gson gson = new Gson();

    public ExternalAPIRequestProcessor(String externalEndPoint, long connectionTimeout, long socketTimeout){
        this.externalEndPoint = externalEndPoint;
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
    }

    @Override
    public Collection<T> process(String query) throws RequestProcessingException {
        Collection<T> results = Collections.emptyList();
        try {
            log.info("Processing search request for {}", query);
            Unirest.setTimeouts(connectionTimeout, socketTimeout);
            GetRequest getRequest = Unirest.get(this.externalEndPoint)
                    .header("accept", "application/json");
            getRequest = replaceParams(getRequest, query);
            HttpResponse<JsonNode> jsonResponse = getRequest.asJson();

            String responseJSONString = jsonResponse.getBody().toString();

            results= (Collection<T>) gson.fromJson(responseJSONString, getType());
            log.info("Search request processed...");
        } catch (UnirestException e) {
            throw new RequestProcessingException("Exception in GoEuro API Call for query:" + query, e);
        }
        return results;
    }

    protected abstract GetRequest replaceParams(GetRequest getRequest, String query);

    protected abstract Type getType();

}
