package com.revature.team4.beans.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.team4.beans.apiResponseDAO.locations.LocationDAO;
import com.revature.team4.beans.apiResponseDAO.locations.LocationEntityDAO;
import com.revature.team4.beans.apiResponseDAO.locations.LocationEntityGroupDAO;
import com.revature.team4.util.DataStore;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @RequestMapping(method = RequestMethod.GET, value = "/{query}")
    public List<LocationEntityDAO> findLocation(@PathVariable String query) {
        //Get the hotels API key
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("api-key.properties");
        try {
            properties.load(input);

            //Create the API request
            StringBuilder urlBuilder = new StringBuilder("https://hotels4.p.rapidapi.com/locations/v2/search?query=");

            StringBuilder queryTerm = new StringBuilder();
            for (int index = 0; index < query.length(); index++) {
                if (query.charAt(index) == ' ') {
                    queryTerm.append("%20");
                } else {
                    queryTerm.append(query.charAt(index));
                }
            }

            urlBuilder.append(queryTerm).append("&locale=en_US&currency=USD");

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(urlBuilder.toString())
                    .get()
                    .addHeader("x-rapidapi-host", "hotels4.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", properties.getProperty("api-key"))
                    .build();

            Response response = client.newCall(request).execute();

            String jsonString = response.body().string();

            //Map the json to object
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            LocationDAO locationDAO = objectMapper.readValue(jsonString, LocationDAO.class);

            //Getting entities at index of 0
            LocationEntityGroupDAO locationEntityGroupDAO = locationDAO.getSuggestions()[0];
            List<LocationEntityDAO> entities = locationEntityGroupDAO.getEntities();

            //Set the data store list to this list of locations and return it
            DataStore.setCurrentLocationResults(entities);
            return entities;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
