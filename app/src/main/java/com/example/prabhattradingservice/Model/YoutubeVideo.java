package com.example.prabhattradingservice.Model;

public class YoutubeVideo {
    private String title;
    private Long id;
    private String videoId;
    private String imageUrl;

    public YoutubeVideo() {
    }

    public YoutubeVideo(String title, Long id, String videoId, String imageUrl) {
        this.title = title;
        this.id = id;
        this.videoId = videoId;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
