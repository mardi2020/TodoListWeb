package com.mardi2020.todoapp.weather.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MainText {

    @JsonProperty("temp")
    private String temp;

    @JsonProperty("feels_like")
    private String feels_like;

    @JsonProperty("temp_min")
    private String temp_min;

    @JsonProperty("temp_max")
    private String  temp_max;

    @JsonProperty("pressure")
    private long pressure;

    @JsonProperty("humidity")
    private long humidity;

}
