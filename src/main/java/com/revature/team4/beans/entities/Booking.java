package com.revature.team4.beans.entities;

import javax.persistence.*;

@Entity
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @Column
    private Long hotelId;
    @Column
    private String name;
    @Column
    private Integer starRating;

    public Booking() {
    }

    public Booking(Integer bookingId, Long hotelId, String name, Integer starRating) {
        this.bookingId = bookingId;
        this.hotelId = hotelId;
        this.name = name;
        this.starRating = starRating;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
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
