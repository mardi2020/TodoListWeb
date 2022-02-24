package com.mardi2020.todoapp.weather.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Coord {

    @JsonProperty("lon")
    private String lon;

    @JsonProperty("lat")
    private String lat;
}
