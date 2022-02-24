package com.mardi2020.todoapp.geoLocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeoResults {

    private String requestId;

    private String returnCode;

    @JsonProperty("geoLocation")
    private GeoLoactionDTO geoLoaction;
}
