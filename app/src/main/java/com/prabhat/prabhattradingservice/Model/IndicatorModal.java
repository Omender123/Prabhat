package com.prabhat.prabhattradingservice.Model;

public class IndicatorModal {
    String id,IndicatorImage,IndicatorVideo;

    public IndicatorModal(String id, String indicatorImage, String indicatorVideo) {
        this.id = id;
        IndicatorImage = indicatorImage;
        IndicatorVideo = indicatorVideo;
    }

    public IndicatorModal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndicatorImage() {
        return IndicatorImage;
    }

    public void setIndicatorImage(String indicatorImage) {
        IndicatorImage = indicatorImage;
    }

    public String getIndicatorVideo() {
        return IndicatorVideo;
    }

    public void setIndicatorVideo(String indicatorVideo) {
        IndicatorVideo = indicatorVideo;
    }
}
