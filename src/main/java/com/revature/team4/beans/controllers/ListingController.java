package com.revature.team4.beans.controllers;

import com.revature.team4.beans.entities.Listing;
import com.revature.team4.beans.entities.User;
import com.revature.team4.beans.repositories.ListingRepo;
import com.revature.team4.beans.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listings")
public class ListingController {

    public final ListingRepo listingRepo;

    @Autowired
    public ListingController(ListingRepo listingRepo) {
        this.listingRepo = listingRepo;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void newListing(@RequestBody Listing listing) { listingRepo.save(listing); }

}
