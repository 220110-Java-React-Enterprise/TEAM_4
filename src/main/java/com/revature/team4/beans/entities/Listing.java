package com.revature.team4.beans.entities;

import javax.persistence.*;

@Entity
public class Listing {
    @Id
    @Column(name = "listing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listingId;

    @Column
    private Long hotelId;

    @Column
    private String name;
    @Column
    private Integer starRating;


    public Listing() {
    }

    public Listing(Integer listingId, Long hotelId, String name, Integer starRating) {
        this.listingId = listingId;
        this.hotelId = hotelId;
        this.name = name;
        this.starRating = starRating;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
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
