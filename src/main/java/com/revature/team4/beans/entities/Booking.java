package com.revature.team4.beans.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class holding booking information, such as id, hotel information, start date and end date
 */
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
    @Column
    private String currentPrice;
    @Column
    private Double exactCurrentPrice;

    @Column
    private Date startDate;
    @Column
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
