package com.mardi2020.todoapp.weather.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Coord {

    @JsonProperty("lon")
    private float lon;

    @JsonProperty("lat")
    private float lat;
}
