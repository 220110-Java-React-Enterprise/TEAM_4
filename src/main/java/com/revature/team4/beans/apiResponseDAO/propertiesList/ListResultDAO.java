package com.revature.team4.beans.apiResponseDAO.propertiesList;

public class ListResultDAO {
    private Integer id;
    private String name;
    private Integer starRating;

    public ListResultDAO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
