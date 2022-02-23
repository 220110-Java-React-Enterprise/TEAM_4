package com.revature.team4.beans.controllers;

import com.revature.team4.beans.entities.User;
import com.revature.team4.beans.repositories.BookingRepo;
import com.revature.team4.beans.repositories.UserRepo;
import com.revature.team4.util.DataStore;
import com.revature.team4.util.ExceptionLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * handles requests/responses regarding User persistence data
 * contains POST, GET, UPDATE and DELETE methods
 * sends commands to User and Booking repositories
 */
@RestController
@RequestMapping("/users")
public class UserController {
    //Repository objects that allow controller methods to access user and booking database tables
    public final UserRepo userRepo;
    public final BookingRepo bookingRepo;

    /**
     * Autowired constructor; Spring injects implementation of repo interfaces
     *
     * @param userRepo
     * @param bookingRepo
     */
    @Autowired
    public UserController(UserRepo userRepo, BookingRepo bookingRepo) {
        this.userRepo = userRepo;
        this.bookingRepo = bookingRepo;
    }

    /**
     * Mapped to the controller's GET method for the /all path
     *
     * @return - list of all users in database
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<User> getAllUsers(){
    return userRepo.findAll();
    }

    /**
     * Mapped to controller's GET method for /{userId} path
     *
     * @param userId
     * @return - specific user by id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User getUserByID(@PathVariable Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }


    /**
     * Checks if a user exists in a database with matching email and password
     * Mapped to the controller's GET method
     *
     * @param email
     * @param password
     * @return - if email and password match/found: user object
     *         - not found: null
     */
    @RequestMapping(method = RequestMethod.GET)
    public User login(@RequestParam String email, @RequestParam String password) {
        //Gather all users from database
        List<User> users = userRepo.findAll();

        for (int index = 0; index < users.size(); index++) {
            //Iterate through users and find user with matching email
            if (users.get(index).getEmail().equals(email)) {
                //If user was found, check if password matches; return user if so, null if not
                if (users.get(index).getPassword().equals(password)) {
                    DataStore.setCurrentUser(users.get(index)); //Can probably eliminate this line
                    return users.get(index);
                } else {
                    return null;
                }
            }
        }

        //Return null if user was not found in database
        return null;
    }

    /**
     * attempt storing a given User object in appropriate table
     * Mapped to the controller's POST method
     *
     * @param user
     * @return - return the User object
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User newUser(@RequestBody User user) {
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            //Log exception and return null if user could not be added to table
            ExceptionLogger.getExceptionLogger().log(e);
            return null;
        }
    }

    //Method to attempt updating a given User object in appropriate table
    //Mapped to the controller's PUT method
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody User user) { userRepo.save(user); }

    //Method to attempt deletion of a given User object in appropriate table
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user) {
        Optional<User> optionalUser = userRepo.findById(user.getUserId());

        //Delete user bookings and user if found in database
        optionalUser.ifPresent(value -> bookingRepo.deleteAll(value.getBookings()));
        userRepo.delete(user);
    }

    //Method to check if an email is already being used in database and return User if so
    public User getUserByEmail(String email) {
        //Get all users in database
        List<User> users = userRepo.findAll();

        //Search users for email; return user if found
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        //Return null if user with email not found
        return null;
    }

    //Method to automatically generate admin account every time the application starts if it does not exist
    //Mapped to controller's POST method with /admin path
    @RequestMapping(method = RequestMethod.POST, value="/admin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createAdmin() {
        //Load admin info from the properties file
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("admin.properties");

        try {
            properties.load(input);

            //Create the user object holding admin info
            User admin = new User(properties.getProperty("email"), properties.getProperty("password"));
            admin.setAdmin(true);

            //Check if admin already exists; save if not
            if (getUserByEmail(admin.getEmail()) == null) {
                userRepo.save(admin);
            }
        } catch (IOException e) {
            ExceptionLogger.getExceptionLogger().log(e);
        }
    }

    //Method to check if a given user is an admin
    //Mapped to controller's GET method with /admin/{userId} path
    @RequestMapping(method = RequestMethod.GET, value="/admin/{userId}")
    public Boolean isAdmin(@PathVariable Integer userId) {
        //Attempt to load user from database
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            //Return true if user was found and is admin; false otherwise
            return user.isAdmin();
        }

        //Return false if user was not found
        return false;
    }
}
