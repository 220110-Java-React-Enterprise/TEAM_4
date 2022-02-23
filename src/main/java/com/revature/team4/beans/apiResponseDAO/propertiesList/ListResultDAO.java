package com.revature.team4.beans.apiResponseDAO.propertiesList;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * The lowest-level model object of data from hotels API response
 * Shows fields such as hotel name, star rating, and current price
 */
public class ListResultDAO {
    private Long id;
    private String name;
    private Integer starRating;
    private String currentPrice;
    private Double exactCurrentPrice;

    public ListResultDAO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getExactCurrentPrice() {
        return exactCurrentPrice;
    }

    public void setExactCurrentPrice(Double exactCurrentPrice) {
        this.exactCurrentPrice = exactCurrentPrice;
    }

    @Override
    public String toString() {
        return "ListResultDAO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", starRating=" + starRating +
                ", currentPrice='" + currentPrice + '\'' +
                ", exactCurrentPrice=" + exactCurrentPrice +
                '}';
    }

    @JsonProperty("ratePlan")
    public void listPrice(Map<String, Object> ratePlan){
        Map<String, Object> price = (Map<String, Object>) ratePlan.get("price");
        this.currentPrice = (String) price.get("current");
        this.exactCurrentPrice = (Double) price.get("exactCurrent");
    }
}
