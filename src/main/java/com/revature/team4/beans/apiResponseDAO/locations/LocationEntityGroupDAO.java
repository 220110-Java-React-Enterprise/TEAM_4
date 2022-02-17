package com.revature.team4.beans.apiResponseDAO.locations;

import java.util.List;

public class LocationEntityGroupDAO {

    public String group;
    public List<LocationEntityDAO> entities;

    public LocationEntityGroupDAO() {
    }

    public LocationEntityGroupDAO(String group, List<LocationEntityDAO> entities) {
        this.group = group;
        this.entities = entities;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<LocationEntityDAO> getEntities() {
        return entities;
    }

    public void setEntities(List<LocationEntityDAO> entities) {
        this.entities = entities;
    }
}
