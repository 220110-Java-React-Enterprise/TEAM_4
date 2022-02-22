package com.revature.team4.beans.repositories;

import com.revature.team4.beans.apiResponseDAO.propertiesList.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
