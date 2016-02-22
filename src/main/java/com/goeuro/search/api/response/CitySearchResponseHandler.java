package com.goeuro.search.api.response;

import com.goeuro.search.api.dto.CityResult;

/**
 * Created by nishantgupta on 22/2/16.
 */
public class CitySearchResponseHandler extends ResponseCSVProcessor<CityResult> {

    public CitySearchResponseHandler(String fileLocation){
        super(fileLocation);
    }
    @Override
    protected String[] convertResult(CityResult result) {
        return new String[]{
            String.valueOf(result.getId()),
            result.getName(),
            result.getType(),
            String.valueOf(result.getGeoPosition().getLatitude()),
            String.valueOf(result.getGeoPosition().getLongitude())
        };
    }

    @Override
    protected String[] getHeadings() {
        return new String[]{
                "id",
                "name",
                "type",
                "latitude",
                "longitude"
        };
    }

}
