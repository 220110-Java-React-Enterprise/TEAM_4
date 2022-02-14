package com.revature.team4.beans.entities;

import javax.persistence.*;

@Entity
public class Listing {
    @Id
    @Column(name = "listing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listingId;

    @Column
    private String date;

    @Column
    private String location;

    public Listing() {
    }

    public Listing(Integer listingId, String date, String location) {
        this.listingId = listingId;
        this.date = date;
        this.location = location;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
