package com.mardi2020.todoapp.weather.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Wind {
    @JsonProperty("speed")
    private Double speed;

    @JsonProperty("deg")
    private long deg;

    @JsonProperty("gust")
    private Double gust;
}
