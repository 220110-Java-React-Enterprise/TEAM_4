package com.revature.team4.beans.controllers;

import com.revature.team4.beans.apiResponseDAO.propertiesList.ListResultDAO;
import com.revature.team4.beans.entities.Booking;
import com.revature.team4.beans.entities.User;
import com.revature.team4.beans.repositories.BookingRepo;
import com.revature.team4.beans.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void newBooking(@RequestBody Booking booking, @PathVariable Integer userId) throws Exception {
        //User user = userRepo.getById(userId);
        Optional<User> optionalUser = userRepo.findById(userId);
        List<ListResultDAO> lRD = findListingsController.findListings();
        System.out.println(booking.getHotelId() + "1");
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            for(int i = 0; i < lRD.size(); i++){
                System.out.println(booking.getHotelId() + "2");
                if(booking.getHotelId().equals(lRD.get(i).getId())){

                    System.out.println(booking.getHotelId() + "3");
                    booking.setName(lRD.get(i).getName());
                    booking.setStarRating(lRD.get(i).getStarRating());
                    user.addBooking(booking);
                    bookingRepo.save(booking);
                    userRepo.save(user);
                }
            }

        } else {
            throw new Exception("User not found...");
        }





    }

}
