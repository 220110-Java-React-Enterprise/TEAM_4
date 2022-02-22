package com.revature.team4.beans.repositories;

import com.revature.team4.beans.apiResponseDAO.propertiesList.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
}
