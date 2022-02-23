package com.revature.team4.util;

import com.revature.team4.beans.apiResponseDAO.locations.LocationEntityDAO;
import com.revature.team4.beans.apiResponseDAO.propertiesList.ListResultDAO;
import com.revature.team4.beans.entities.User;

import java.util.List;

//Class to hold static members that can be accessed easily throughout the application
public class DataStore {
    private static User currentUser;
    private static List<LocationEntityDAO> currentLocationResults;
    private static LocationEntityDAO currentLocation;
    private static List<ListResultDAO> currentListingsResults;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        DataStore.currentUser = currentUser;
    }

    public static List<LocationEntityDAO> getCurrentLocationResults() {
        return currentLocationResults;
    }

    public static void setCurrentLocationResults(List<LocationEntityDAO> currentLocationResults) {
        DataStore.currentLocationResults = currentLocationResults;
    }

    public static LocationEntityDAO getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(LocationEntityDAO currentLocation) {
        DataStore.currentLocation = currentLocation;
    }

    public static List<ListResultDAO> getCurrentListingsResults() {
        return currentListingsResults;
    }

    public static void setCurrentListingsResults(List<ListResultDAO> currentListingsResults) {
        DataStore.currentListingsResults = currentListingsResults;
    }
}
