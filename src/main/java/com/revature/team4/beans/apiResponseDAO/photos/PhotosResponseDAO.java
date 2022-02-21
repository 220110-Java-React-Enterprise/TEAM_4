package com.revature.team4.beans.apiResponseDAO.photos;

//Class that holds the API response from a photo search with given hotel id
public class PhotosResponseDAO {
    private Long hotelId;
    private HotelImage[] hotelImages;

    public PhotosResponseDAO() {
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public HotelImage[] getHotelImages() {
        return hotelImages;
    }

    public void setHotelImages(HotelImage[] hotelImages) {
        this.hotelImages = hotelImages;
    }

    //Subclass holding images within the json response
    public static class HotelImage {
        private String baseUrl;
        private Long imageId;
        private String mediaGUID;
        private ImageSize[] sizes;

        public HotelImage() {
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Long getImageId() {
            return imageId;
        }

        public void setImageId(Long imageId) {
            this.imageId = imageId;
        }

        public String getMediaGUID() {
            return mediaGUID;
        }

        public void setMediaGUID(String mediaGUID) {
            this.mediaGUID = mediaGUID;
        }

        public ImageSize[] getSizes() {
            return sizes;
        }

        public void setSizes(ImageSize[] sizes) {
            this.sizes = sizes;
        }

        //Method to return the url with a given suffix
        public String getURLWithSuffix(String suffix) {
            int indexOfBracket = this.getBaseUrl().indexOf('{');

            String returnString = this.getBaseUrl().substring(0, indexOfBracket);
            returnString += suffix;
            return returnString + this.getBaseUrl().substring(indexOfBracket + 6);
        }
    }

    public static class ImageSize {
        private Integer type;
        private Character suffix;

        public ImageSize() {
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Character getSuffix() {
            return suffix;
        }

        public void setSuffix(Character suffix) {
            this.suffix = suffix;
        }
    }
}
