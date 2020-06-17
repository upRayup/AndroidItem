package com.example.homework_practicing;

import java.io.Serializable;

public class Fruit implements Serializable {
    private String name;
    private String Intro;
    private int imageId;
    private String Price;
    public Fruit(String name, int imageId,String Intro,String Price) {
        this.name = name;
        this.imageId = imageId;
        this.Intro=Intro;
        this.Price=Price;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }
    public String getIntro(){
        return Intro;
    }
    public String getPrice(){
        return Price;
    } }