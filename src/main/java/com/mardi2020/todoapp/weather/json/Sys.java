package com.mardi2020.todoapp.weather.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Sys {

    @JsonProperty("type")
    private long type;

    @JsonProperty("id")
    private long id;

    @JsonProperty("country")
    private String country;

    @JsonProperty("sunrise")
    private long sunrise;

    @JsonProperty("sunset")
    private long sunset;
}
