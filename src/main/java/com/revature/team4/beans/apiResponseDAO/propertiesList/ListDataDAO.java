package com.revature.team4.beans.apiResponseDAO.propertiesList;

/**
 * A high-level model object of data from hotels API response
 */
public class ListDataDAO {
    private ListBodyDAO body;

    public ListDataDAO() {
    }

    public ListBodyDAO getBody() {
        return body;
    }

    public void setBody(ListBodyDAO body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ListDataDAO{" +
                "body=" + body +
                '}';
    }
}
