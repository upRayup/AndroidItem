package com.example.beiwanglu;

public class MemoBean {
    private String title;
    private String content;
    private String imgpath;
    private String time;
    public MemoBean(String title, String content, String imgpath, String time) {
        this.title = title;
        this.content = content;
        this.imgpath = imgpath;
        this.time = time;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImgpath() {
        return imgpath;
    }
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    } }
