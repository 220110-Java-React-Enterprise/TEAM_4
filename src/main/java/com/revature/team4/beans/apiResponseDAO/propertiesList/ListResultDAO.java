package com.revature.team4.beans.apiResponseDAO.propertiesList;

public class ListResultDAO {
    private Long id;
    private String name;
    private Integer starRating;

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

    @Override
    public String toString() {
        return "ListResultDAO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", starRating=" + starRating +
                '}';
    }
}
