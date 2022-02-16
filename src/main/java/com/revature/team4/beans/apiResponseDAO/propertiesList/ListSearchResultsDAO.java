package com.revature.team4.beans.apiResponseDAO.propertiesList;

import java.util.ArrayList;

public class ListSearchResultsDAO {
    private Integer totalCount;
    private ArrayList<ListResultDAO> results;

    public ListSearchResultsDAO() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<ListResultDAO> getResults() {
        return results;
    }

    public void setResults(ArrayList<ListResultDAO> results) {
        this.results = results;
    }
}
