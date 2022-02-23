package com.revature.team4.beans.controllers;

import com.revature.team4.beans.apiResponseDAO.photos.PhotosResponseDAO;
import com.revature.team4.beans.apiResponseDAO.propertiesList.ListResultDAO;
import com.revature.team4.util.DataStore;
import com.revature.team4.util.ExceptionLogger;
import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.team4.beans.apiResponseDAO.propertiesList.PropertyListDAO;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * handles requests to and responses from third-party hotels API
 * contains GET method for hotels and hotel photos
 */
@RestController
@RequestMapping("/location_listings")
public class FindListingsController {

    /**
     *
     * @param query - takes in a location ID
     * @return - hotel listings for given location, in the form of an arraylist
     *
     * this is the core of interacting with API -
     * the API response is picked at little by little until we get to the data we want
     */
    @RequestMapping(method = RequestMethod.GET, value="/{query}")
    public ArrayList<ListResultDAO> findListings(@PathVariable String query){
        //Get api key from properties file
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("api-key.properties");
        try{
            props.load(input);
            OkHttpClient client = new OkHttpClient();
            StringBuilder urlBuilder = new StringBuilder("https://hotels4.p.rapidapi.com/properties/list?destinationId=");
            urlBuilder.append(query);
            urlBuilder.append("&pageNumber=1&pageSize=25&checkIn=2020-01-08&checkOut=2020-01-15&adults1=1&sortOrder=PRICE&locale=en_US&currency=USD");

            Request request = new Request.Builder()
                    .url(urlBuilder.toString()) //"https://hotels4.p.rapidapi.com/properties/list?destinationId=1506246&pageNumber=1&pageSize=25&checkIn=2020-01-08&checkOut=2020-01-15&adults1=1&sortOrder=PRICE&locale=en_US&currency=USD")
                    .get()
                    .addHeader("x-rapidapi-host", "hotels4.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", props.getProperty("api-key"))
                    .build();
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();
            //System.out.println(jsonString);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PropertyListDAO propertyListDAO = mapper.readValue(jsonString, PropertyListDAO.class);

            ArrayList<ListResultDAO> listings;
            listings = propertyListDAO.getData().getBody().getSearchResults().getResults();
//            System.out.println(listings);

            //Set data store object to given listings and return
            DataStore.setCurrentListingsResults(listings);
            return listings;
        } catch (IOException e) {
            ExceptionLogger.getExceptionLogger().log(e);
        }
    return null;
    }

    /**
     *
     * @param query
     * @return - hotel photo object
     *
     */
    @RequestMapping(method = RequestMethod.GET, value="/photos/{query}")
    public PhotosResponseDAO findPhotos(@PathVariable String query) {
        //Get api key from properties file
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("api-key.properties");

        try{
            props.load(input);

            //Build http request
            OkHttpClient client = new OkHttpClient();
            StringBuilder urlBuilder = new StringBuilder("https://hotels4.p.rapidapi.com/properties/get-hotel-photos?id=");
            urlBuilder.append(query);

            Request request = new Request.Builder()
                    .url(urlBuilder.toString())
                    .get()
                    .addHeader("x-rapidapi-host", "hotels4.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", props.getProperty("api-key"))
                    .build();

            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();

            //Map the json to the photo respose DAO
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PhotosResponseDAO photosResponseDAO = mapper.readValue(jsonString, PhotosResponseDAO.class);

            return photosResponseDAO;
        } catch (IOException e) {
            ExceptionLogger.getExceptionLogger().log(e);
        }

        return null;
    }
}
