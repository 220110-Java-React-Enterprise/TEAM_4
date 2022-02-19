package com.revature.team4.beans.controllers;

import com.revature.team4.beans.entities.User;
import com.revature.team4.beans.repositories.BookingRepo;
import com.revature.team4.beans.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserRepo userRepo;
    public final BookingRepo bookingRepo;

    @Autowired
    public UserController(UserRepo userRepo, BookingRepo bookingRepo) {
        this.userRepo = userRepo;
        this.bookingRepo = bookingRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Integer login(@RequestParam String email, @RequestParam String password) {
        List<User> users = userRepo.findAll();

        for (int index = 0; index < users.size(); index++) {
            if (users.get(index).getEmail().equals(email)) {
                if (users.get(index).getPassword().equals(password)) {
                    return users.get(index).getUserId();
                } else {
                    return 0;
                }
            }
        }

        return 0;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void newUser(@RequestBody User user) {
        userRepo.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody User user) { userRepo.save(user); }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user) {
        Optional<User> optionalUser = userRepo.findById(user.getUserId());
        optionalUser.ifPresent(value -> bookingRepo.deleteAll(value.getBookings()));
        userRepo.delete(user);
    }
}
