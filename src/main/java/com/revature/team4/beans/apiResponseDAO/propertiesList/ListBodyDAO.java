package com.revature.team4.beans.apiResponseDAO.propertiesList;

/**
 * A mid-level model object of data from hotels API response
 */
public class ListBodyDAO {
    private String header;
    private ListSearchResultsDAO searchResults;

    public ListBodyDAO() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ListSearchResultsDAO getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(ListSearchResultsDAO searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public String toString() {
        return "ListBodyDAO{" +
                "header='" + header + '\'' +
                ", searchResults=" + searchResults +
                '}';
    }
}
