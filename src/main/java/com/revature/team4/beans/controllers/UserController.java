package com.revature.team4.beans.controllers;

import com.revature.team4.beans.entities.User;
import com.revature.team4.beans.repositories.BookingRepo;
import com.revature.team4.beans.repositories.UserRepo;
import com.revature.team4.util.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

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
    public User login(@RequestParam String email, @RequestParam String password) {
        List<User> users = userRepo.findAll();

        for (int index = 0; index < users.size(); index++) {
            if (users.get(index).getEmail().equals(email)) {
                if (users.get(index).getPassword().equals(password)) {
                    DataStore.setCurrentUser(users.get(index));
                    return users.get(index);
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User newUser(@RequestBody User user) {
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    //Method to check if an email is already being used in database and return User if so
    public User isEmailUnused(String email) {
        //Get all users in database
        List<User> users = userRepo.findAll();

        //Placeholder for commit
        return null;
    }

    //Method to automatically generate admin account every time the application starts if it does not exist
    public void createAdmin() {
        //Load admin info from the properties file
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("admin.properties");

        try {
            properties.load(input);

            //Create the user object holding admin info
            User admin = new User(properties.getProperty("email"), properties.getProperty("password"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
