package com.revature.team4;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.revature.team4.beans.apiResponseDAO.propertiesList.ListResultDAO;
import com.revature.team4.beans.controllers.FindListingsController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Team4Application {

	public static void main(String[] args) throws JsonProcessingException {
		List<ListResultDAO> list;
		System.out.println("Team4");
		SpringApplication.run(Team4Application.class, args);
		FindListingsController fl = new FindListingsController();
		System.out.println(fl.findListings());
	}

}
