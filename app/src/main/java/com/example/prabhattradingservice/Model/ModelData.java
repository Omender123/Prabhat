package com.example.prabhattradingservice.Model;

public class ModelData {
private String /*Image,*/FirstTxt,SecondTxt;
private int Images;


  /*  public ModelData(String image, String firstTxt, String secondTxt) {
        Image = image;
        FirstTxt = firstTxt;
        SecondTxt = secondTxt;
    }*/

    public ModelData(String firstTxt, String secondTxt, Integer images) {
        FirstTxt = firstTxt;
        SecondTxt = secondTxt;
        Images = images;
    }

    public ModelData() {
    }

   /* public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }*/

    public Integer getImages() {
        return Images;
    }

    public void setImages(Integer images) {
        Images = images;
    }

    public String getFirstTxt() {
        return FirstTxt;
    }

    public void setFirstTxt(String firstTxt) {
        FirstTxt = firstTxt;
    }

    public String getSecondTxt() {
        return SecondTxt;
    }

    public void setSecondTxt(String secondTxt) {
        SecondTxt = secondTxt;
    }
}
