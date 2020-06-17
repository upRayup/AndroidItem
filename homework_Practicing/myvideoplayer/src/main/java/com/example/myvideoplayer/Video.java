package com.example.myvideoplayer;

public class Video {
    private String videoname;
    private String videourl;

    public String getVideoname() {
        return videoname;
    }

    public String getVideourl() {
        return videourl;
    }

    public Video(String videoname, String videourl) {
        this.videoname = videoname;
        this.videourl = videourl;
    }
}
