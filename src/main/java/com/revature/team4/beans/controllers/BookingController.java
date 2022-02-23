package com.revature.team4.beans.controllers;

import com.revature.team4.beans.apiResponseDAO.propertiesList.ListResultDAO;
import com.revature.team4.beans.entities.Booking;
import com.revature.team4.beans.entities.User;
import com.revature.team4.beans.repositories.BookingRepo;
import com.revature.team4.beans.repositories.UserRepo;
import com.revature.team4.util.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * handles requests/responses regarding hotel bookings
 * contains POST, GET, UPDATE and DELETE methods
 * sends commands to User and Booking repositories
 */
@RestController
@RequestMapping("/users/{userId}/bookings")
public class BookingController {

    public final BookingRepo bookingRepo;
    public final UserRepo userRepo;

    @Autowired
    public BookingController(BookingRepo bookingRepo, UserRepo userRepo) {
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
    }

    /**
     * @param booking
     * @param userId
     * @param startDate
     * @param endDate
     * @throws Exception
     *
     * takes in new booking object
     * saves it to persistence
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void newBooking(@RequestBody Booking booking, @PathVariable Integer userId,
                           @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                           @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) throws Exception {
        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            //Set the start and end date to what was passed in as request parameters
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);

            //Add booking to user list and save to database
            user.addBooking(booking);
            bookingRepo.save(booking);
            userRepo.save(user);
        } else {
            throw new Exception("User not found...");
        }
    }

    /**
     *
     * @return - a list of all bookings from every user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    /**
     *
     * @param userId
     * @return - list of all bookings for user specified by userId
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Booking> getAllBookingsForUser(@PathVariable Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getBookings();
        } else {
            return null;
        }
    }

    /**
     *
     * @param userId
     * @param bookingId
     * @return - booking specified by bookingId for user specified by userId
     */
    @RequestMapping(value = "/{bookingId}")
    public Booking getBookingForUserById(@PathVariable Integer userId, @PathVariable Integer bookingId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            for (Booking b : optionalUser.get().getBookings()) {
                if (b.getBookingId().equals(bookingId)) {
                    return b;
                }
            }
        }

        return null;
    }

    /**
     *
     * @param updateBooking
     * @param bookingId
     * @param startDate
     * @param endDate
     *
     * saves user's changes to the dates of a specified booking
     */
    @RequestMapping(value = "/{bookingId}", method = RequestMethod.PUT)
    public void updateBooking(@RequestBody Booking updateBooking, @PathVariable Integer bookingId,
                              @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                              @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);
        if(optionalBooking.isPresent() && updateBooking.getBookingId().equals(bookingId)) {
            updateBooking.setStartDate(startDate);
            updateBooking.setEndDate(endDate);
            bookingRepo.save(updateBooking);
        }
    }

    /**
     *
     * @param userId
     * @param bookingId
     *
     * deletes a booking from persistence
     */
    @RequestMapping(value = "/{bookingId}", method = RequestMethod.DELETE)
    public void deleteBooking(@PathVariable Integer userId, @PathVariable Integer bookingId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            for (Booking b : user.getBookings()) {
                if(bookingId.equals(b.getBookingId())) {
                    user.removeBooking(b);
                    bookingRepo.delete(b);
                    userRepo.save(user);
                    break;
                }
            }
        }
    }

}
