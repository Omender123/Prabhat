package com.prabhat.prabhattradingservice.Model;

public class BlogsModel {
    String id,h1,h2, BlogsImage;

    public BlogsModel(String id, String h1, String h2, String blogsImage) {
        this.id = id;
        this.h1 = h1;
        this.h2 = h2;
        BlogsImage = blogsImage;
    }

    public BlogsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getBlogsImage() {
        return BlogsImage;
    }

    public void setBlogsImage(String blogsImage) {
        BlogsImage = blogsImage;
    }
}
