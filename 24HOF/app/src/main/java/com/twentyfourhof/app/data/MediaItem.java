package com.twentyfourhof.app.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MediaItem {

    private String name = "";
    private String text = "";
    private boolean favorised;
    private User user = new User();
    private long timeStamp = 0;


    @JsonProperty("pic")
    private String pictureURL = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() { return text; }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFavorised() {return favorised;}

    public void setFavorised(boolean favorised) {this.favorised = favorised;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
