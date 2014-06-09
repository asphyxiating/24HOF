package com.twentyfourhof.app.data;

/**
 * Created by kannzaubern on 22.05.14.
 */

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name = "";
    private String email = "";
    private String imagePath = "";
    private String age = "";
    private List<MediaItem> items = new ArrayList<MediaItem>();
    private Bitmap bitmap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MediaItem> getItems() {
        return items;
    }

    public void setItems(List<MediaItem> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge (String genre) {
        this.age = age;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}


