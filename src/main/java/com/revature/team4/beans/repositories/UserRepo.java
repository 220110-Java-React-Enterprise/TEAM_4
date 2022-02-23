package com.revature.team4.beans.repositories;

import com.revature.team4.beans.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that extends JpaRepository interface
 * with User object and Integer id in place of generics
 * implementation handled by Spring JPA
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}