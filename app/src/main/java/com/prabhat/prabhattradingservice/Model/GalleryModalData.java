package com.prabhat.prabhattradingservice.Model;

public class GalleryModalData {
    String id;
    String image;

    public GalleryModalData(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public GalleryModalData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
