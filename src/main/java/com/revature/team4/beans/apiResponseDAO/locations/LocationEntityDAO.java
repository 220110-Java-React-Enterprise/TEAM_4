package com.revature.team4.beans.apiResponseDAO.locations;

/**
 * The lowest-level model object of data taken from locations API
 * containing information such as name, latitude/longitude, and whether it is a city or neighborhood
 */
public class LocationEntityDAO {

    public String geoId;
    public String destinationId;
    public Object landmarkCityDestinationId = null;
    public String type;
    public String redirectPage;
    public Double latitude;
    public Double longitude;
    public Object searchDetail = null;
    public String caption;
    public String name;

    public LocationEntityDAO() {
    }

    public LocationEntityDAO(String geoId,
                             String destinationId,
                             Object landmarkCityDestinationId,
                             String type,
                             String redirectPage,
                             Double latitude,
                             Double longitude,
                             Object searchDetail,
                             String caption,
                             String name) {
        this.geoId = geoId;
        this.destinationId = destinationId;
        this.landmarkCityDestinationId = landmarkCityDestinationId;
        this.type = type;
        this.redirectPage = redirectPage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.searchDetail = searchDetail;
        this.caption = caption;
        this.name = name;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public Object getLandmarkCityDestinationId() {
        return landmarkCityDestinationId;
    }

    public void setLandmarkCityDestinationId(Object landmarkCityDestinationId) {
        this.landmarkCityDestinationId = landmarkCityDestinationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRedirectPage() {
        return redirectPage;
    }

    public void setRedirectPage(String redirectPage) {
        this.redirectPage = redirectPage;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Object getSearchDetail() {
        return searchDetail;
    }

    public void setSearchDetail(Object searchDetail) {
        this.searchDetail = searchDetail;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LocationEntityDAO{" +
                "geoId='" + geoId + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", landmarkCityDestinationId=" + landmarkCityDestinationId +
                ", type='" + type + '\'' +
                ", redirectPage='" + redirectPage + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", searchDetail=" + searchDetail +
                ", caption='" + caption + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
