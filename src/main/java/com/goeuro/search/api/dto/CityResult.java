package com.goeuro.search.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by nishantgupta on 22/2/16.
 */
@Data
public class CityResult {
    @SerializedName("_id")
    private long id;
    private String name;
    //type as enum ?
    private String type;

    @SerializedName("geo_position")
    private GeoPosition geoPosition;

    @Data
    public static class GeoPosition{
        private double latitude;
        private double longitude;
    }
}
