package com.mardi2020.todoapp.weather;

import com.mardi2020.todoapp.weather.json.*;
import lombok.Data;

import java.util.List;

@Data
public class WeatherDTO {

    private Coord coord;

    private List<Weather> weather;

    private MainText main;

    private long visibility;

    private Wind wind;

    private long dt;

    private Sys sys;

    private long timezone;

    private long id;

    private String name;

}
