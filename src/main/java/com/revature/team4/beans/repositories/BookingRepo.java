package com.revature.team4.beans.repositories;

import com.revature.team4.beans.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends JpaRepository interface
 * with Booking object and Booking id in place of generics
 * implementation handled by Spring JPA
 */
@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
}
