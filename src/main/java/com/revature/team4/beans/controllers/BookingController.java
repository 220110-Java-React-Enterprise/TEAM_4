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

@RestController
@RequestMapping("/users/{userId}/bookings")
public class BookingController {
    FindListingsController findListingsController = new FindListingsController();

    public final BookingRepo bookingRepo;
    public final UserRepo userRepo;

    @Autowired
    public BookingController(BookingRepo bookingRepo, UserRepo userRepo) {
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void newBooking(@RequestBody Booking booking, @PathVariable Integer userId,
                           @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                           @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) throws Exception {
        Optional<User> optionalUser = userRepo.findById(userId);
        List<ListResultDAO> lRD = DataStore.getCurrentListingsResults();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            for(int i = 0; i < lRD.size(); i++){
                if(booking.getHotelId().equals(lRD.get(i).getId())){
                    booking.setName(lRD.get(i).getName());
                    booking.setStarRating(lRD.get(i).getStarRating());

                    booking.setStartDate(startDate);
                    booking.setEndDate(endDate);

                    user.addBooking(booking);
                    bookingRepo.save(booking);
                    userRepo.save(user);
                }
            }
        } else {
            throw new Exception("User not found...");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Booking> getAllBookingsForUser(@PathVariable Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getBookings();
        } else {
            return null;
        }
    }

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

    @RequestMapping(value = "/{bookingId}", method = RequestMethod.DELETE)
    public void deleteBooking(@PathVariable Integer userId, @PathVariable Integer bookingId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            for (Booking b : user.getBookings()) {
                user.removeBooking(b);
                bookingRepo.delete(b);
                userRepo.save(user);
                break;
            }
        }
    }

}
