package com.example.restApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.restApi.cache.AppCache;
import com.example.restApi.constants.Placeholders;
import com.example.restApi.response.WeatherResponse;

@Service
public class WeatherService{

    private static final String apiKey = "6773db46a8df04837f85976722575397";
    
    // private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){

        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);

        if(weatherResponse != null){
            return weatherResponse;
        }

        else{
            String finalApi = appCache.APP_CACHE.get("weather_api").replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();

            if(body != null){
                redisService.set("weather_of_" + city, body, 300l);
            }

            return body;
        }
    }
}

