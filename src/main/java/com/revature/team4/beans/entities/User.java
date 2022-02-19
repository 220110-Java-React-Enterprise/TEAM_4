package com.revature.team4.beans.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true)
    private String email;

    @Column
    private String password;


    @Column
    @OneToMany
    private List<Booking> bookings = new LinkedList<>();

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public void removeBooking(Booking booking){
        bookings.remove(booking);
    }


    public User() {
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
