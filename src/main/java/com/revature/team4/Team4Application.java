package com.revature.team4;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.revature.team4.beans.apiResponseDAO.propertiesList.ListResultDAO;
import com.revature.team4.beans.controllers.FindListingsController;
import com.revature.team4.beans.controllers.UserController;
import com.revature.team4.beans.repositories.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Team4Application {
	public static void main(String[] args) throws JsonProcessingException {
		System.out.println("Team4");
		SpringApplication.run(Team4Application.class, args);
	}

}
