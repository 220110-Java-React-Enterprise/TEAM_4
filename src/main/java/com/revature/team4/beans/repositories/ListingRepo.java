package com.revature.team4.beans.repositories;

import com.revature.team4.beans.entities.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepo extends JpaRepository<Listing, Integer> {
}
