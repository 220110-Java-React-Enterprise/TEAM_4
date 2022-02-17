package com.revature.team4.beans.controllers;

import com.revature.team4.beans.entities.User;
import com.revature.team4.beans.repositories.ListingRepo;
import com.revature.team4.beans.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo, ListingRepo listingRepo) {
        this.userRepo = userRepo;

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void newUser(@RequestBody User user) { userRepo.save(user); }
}
