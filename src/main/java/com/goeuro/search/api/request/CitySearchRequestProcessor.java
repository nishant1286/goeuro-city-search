package com.goeuro.search.api.request;

import com.goeuro.search.api.dto.CityResult;
import com.goeuro.search.api.response.exception.ResultProcessingException;
import com.goeuro.search.api.util.PropertyUtil;
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
public class CitySearchRequestProcessor extends ExternalAPIRequestProcessor<CityResult> {

    private static final String citySearchGoEuroEndPointKey = "goeuro.city.search.api.end.point";
    private static final String connectionTimeoutKey =  "goeuro.api.connection.timeout";
    private static final String socketTimeoutKey = "goeuro.api.socket.timeout";

    private static final Type type = new TypeToken<Collection<CityResult>>() {}.getType();

    private static final String cityNameRouteParam = "CITY_NAME";

    public CitySearchRequestProcessor(){
        super(PropertyUtil.getValue(citySearchGoEuroEndPointKey),
              Long.parseLong(PropertyUtil.getValue(connectionTimeoutKey)),
              Long.parseLong(PropertyUtil.getValue(socketTimeoutKey)));
    }

    @Override
    protected GetRequest replaceParams(GetRequest getRequest, String query) {
        getRequest.routeParam(cityNameRouteParam, query);
        return getRequest;
    }

    @Override
    protected Type getType() {
        return type;
    }
}
