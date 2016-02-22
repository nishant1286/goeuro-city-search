package com.goeuro.search.api;

import com.goeuro.search.api.dto.CityResult;
import com.goeuro.search.api.request.CitySearchRequestProcessor;
import com.goeuro.search.api.request.exception.RequestProcessingException;
import com.goeuro.search.api.response.CitySearchResponseHandler;
import com.goeuro.search.api.response.exception.ResultProcessingException;
import com.goeuro.search.api.util.PropertyUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * Created by nishantgupta on 22/2/16.
 */
@Slf4j
public class CitySearchService {

    private static CitySearchRequestProcessor citySearchRequestProcessor = new CitySearchRequestProcessor();
    private static CitySearchResponseHandler citySearchResponseHandler = new CitySearchResponseHandler(PropertyUtil.getValue("city.search.response.writer.folder.location"));

    public static void main(String[] args){
        if(args == null || args.length == 0 ){
            log.error("Please provide valid arguments [Needs 1 argument as city name]");
            return;
        }
        if(args.length > 1){
            log.warn("Please note that only 1st argument is used in service.");
        }
        String searchedQuery = args[0];
        try {
            Collection<CityResult> results =  citySearchRequestProcessor.process(searchedQuery);
            citySearchResponseHandler.process(searchedQuery, results);
        }catch (RequestProcessingException e) {
            log.error("Exception in query request processing :" + searchedQuery, e);
        }catch (ResultProcessingException e) {
            log.error("Exception in query response processing :" + searchedQuery, e);
        }

    }
}
