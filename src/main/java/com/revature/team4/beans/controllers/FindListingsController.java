package com.revature.team4.beans.controllers;

import com.revature.team4.beans.apiResponseDAO.propertiesList.ListResultDAO;
import com.revature.team4.util.DataStore;
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


@RestController
@RequestMapping("/find_listings")
public class FindListingsController {

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ListResultDAO> findListings(@RequestParam String query){
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
            e.printStackTrace();
        }
    return null;
    }
}
