package com.prabhat.prabhattradingservice.Model;

public class YoutubeImagesModel {
    private String imageUrl;

    public YoutubeImagesModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public YoutubeImagesModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
