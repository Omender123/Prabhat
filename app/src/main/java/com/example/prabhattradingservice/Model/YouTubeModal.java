package com.example.prabhattradingservice.Model;

public class YouTubeModal {
    String id,youtubeImage,youtubeVideo;

    public YouTubeModal(String id, String youtubeImage, String youtubeVideo) {
        this.id = id;
        this.youtubeImage = youtubeImage;
        this.youtubeVideo = youtubeVideo;
    }

    public YouTubeModal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYoutubeImage() {
        return youtubeImage;
    }

    public void setYoutubeImage(String youtubeImage) {
        this.youtubeImage = youtubeImage;
    }

    public String getYoutubeVideo() {
        return youtubeVideo;
    }

    public void setYoutubeVideo(String youtubeVideo) {
        this.youtubeVideo = youtubeVideo;
    }
}
