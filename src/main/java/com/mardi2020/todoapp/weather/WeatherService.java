package com.mardi2020.todoapp.weather;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mardi2020.todoapp.geoLocation.GeoLocationService;
import com.mardi2020.todoapp.geoLocation.GeoResults;
import com.mardi2020.todoapp.weather.json.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weatherKey}")
    private String key;

    @Autowired
    private GeoLocationService geoLocationService;

    private String getWeather(String lat, String lon){
        StringBuilder stringBuilder = new StringBuilder();

        try {
            String APIUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + key;

            URL url = new URL(APIUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bufferedReader;

            if(urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
            urlConnection.disconnect();


        } catch (Exception e){
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public WeatherDTO filteringInfo(String latitude, String longitude) {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        WeatherDTO weather = new WeatherDTO();
        try {

            String lat = latitude;
            String lon = longitude;
            String results = getWeather(lat, lon);

            weather = mapper.readValue(results, WeatherDTO.class);

            // ????????? ????????? ??????
            weather.getMain().setTemp(toCelcius(weather.getMain().getTemp()));
            weather.getMain().setTemp_min(toCelcius(weather.getMain().getTemp_min()));
            weather.getMain().setTemp_max(toCelcius(weather.getMain().getTemp_max()));

            List<Weather> weatherList = weather.getWeather();

            String postfix = "@2x.png";
            for (Weather w : weatherList) {
                StringBuilder iconUrl = new StringBuilder("http://openweathermap.org/img/wn/");
                String icon = w.getIcon(); // 01d
                iconUrl.append(icon);
                iconUrl.append(postfix);
                w.setIcon(iconUrl.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    private String toCelcius(String temp) {
        double tmp = Double.parseDouble(temp);
        return Integer.toString((int) (tmp - 273.15));
    }
}
