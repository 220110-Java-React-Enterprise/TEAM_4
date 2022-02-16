package com.revature.team4.beans.apiResponseDAO.propertiesList;

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
}
