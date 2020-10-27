package com.example.prabhattradingservice.Model;

public class PositionalModal {
    String id,PositionalImage,PositionalVideo;

    public PositionalModal(String id, String positionalImage, String positionalVideo) {
        this.id = id;
        PositionalImage = positionalImage;
        PositionalVideo = positionalVideo;
    }

    public PositionalModal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionalImage() {
        return PositionalImage;
    }

    public void setPositionalImage(String positionalImage) {
        PositionalImage = positionalImage;
    }

    public String getPositionalVideo() {
        return PositionalVideo;
    }

    public void setPositionalVideo(String positionalVideo) {
        PositionalVideo = positionalVideo;
    }
}
