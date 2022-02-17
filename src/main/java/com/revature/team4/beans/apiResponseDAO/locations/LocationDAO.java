package com.revature.team4.beans.apiResponseDAO.locations;

public class LocationDAO {
    public String term;
    public Integer moresuggestions;
    public Object autoSuggestInstance = null;
    public String trackingID;
    public boolean misspellingfallback;
    public LocationEntityGroupDAO[] suggestions;
    public boolean geocodeFallback;

    public LocationDAO() {
    }

    public LocationDAO(String term,
                       Integer moresuggestions,
                       Object autoSuggestInstance,
                       String trackingID,
                       boolean misspellingfallback,
                       LocationEntityGroupDAO[] suggestions,
                       boolean geocodeFallback) {
        this.term = term;
        this.moresuggestions = moresuggestions;
        this.autoSuggestInstance = autoSuggestInstance;
        this.trackingID = trackingID;
        this.misspellingfallback = misspellingfallback;
        this.suggestions = suggestions;
        this.geocodeFallback = geocodeFallback;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getMoresuggestions() {
        return moresuggestions;
    }

    public void setMoresuggestions(Integer moresuggestions) {
        this.moresuggestions = moresuggestions;
    }

    public Object getAutoSuggestInstance() {
        return autoSuggestInstance;
    }

    public void setAutoSuggestInstance(Object autoSuggestInstance) {
        this.autoSuggestInstance = autoSuggestInstance;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }

    public boolean isMisspellingfallback() {
        return misspellingfallback;
    }

    public void setMisspellingfallback(boolean misspellingfallback) {
        this.misspellingfallback = misspellingfallback;
    }

    public LocationEntityGroupDAO[] getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(LocationEntityGroupDAO[] suggestions) {
        this.suggestions = suggestions;
    }

    public boolean isGeocodeFallback() {
        return geocodeFallback;
    }

    public void setGeocodeFallback(boolean geocodeFallback) {
        this.geocodeFallback = geocodeFallback;
    }
}
