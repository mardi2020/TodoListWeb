package com.mardi2020.todoapp.geoLocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeoLoactionDTO {

    @JsonProperty("country")
    private String country;

    @JsonProperty("r1")
    private String r1;

    @JsonProperty("r2")
    private String r2;

    @JsonProperty("r3")
    private String r3;

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("long")
    private String lon;

}
