package com.twentyfourhof.app.data.persistence;

import android.content.SharedPreferences;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.inject.Inject;
import com.twentyfourhof.app.data.MediaItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaItemPersister {

    private static final String ITEM = "item";

    @Inject
    private ObjectMapper objectMapper;
    @Inject
    private SharedPreferences sharedPreferences;

    public MediaItemPersister(){};

    public void add(MediaItem item) throws IOException {
        List<MediaItem> items = getAll();
        items.add(item);
        Log.d(MediaItemPersister.class.getSimpleName(), "string: " + objectMapper.writeValueAsString(items));
        sharedPreferences.edit().putString(ITEM, objectMapper.writeValueAsString(items)).commit();
    }

    public List<MediaItem> getAll() throws IOException {
        String itemString = sharedPreferences.getString(ITEM, "");
        if (itemString == "") {
            return new ArrayList<MediaItem>();
        } else {
            return objectMapper.readValue(itemString, TypeFactory.defaultInstance().constructCollectionType(List.class, MediaItem.class));
        }
    }

    public void saveAll() {
        sharedPreferences.edit().putString(ITEM, "[{\"address\":\"Kuss Kuss Kuche\",\"artist\":{\"email\":\"blackatgmail.com\",\"genre\":\"Folk\",\"gigs\":[],\"name\":\"Black Riding Till We Die\"},\"location\":null,\"name\":\"Kuss kuss Open Mic\"},{\"address\":\"Artliners\",\"artist\":{\"email\":\"blackatgmail.com\",\"genre\":\"Folk\",\"gigs\":[],\"name\":\"Black Riding Till We Die\"},\"location\":null,\"name\":\"Big Ho Down\"},{\"address\":\"Madame Claudes\",\"artist\":{\"email\":\"blackatgmail.com\",\"genre\":\"Folk\",\"gigs\":[],\"name\":\"Black Riding Till We Die\"},\"location\":null,\"name\":\"Singing and Playing\"},{\"address\":\"O2 Areana \",\"artist\":{\"email\":\"vincentemail\",\"genre\":\"Singer Song Writer\",\"gigs\":[],\"name\":\"Vincent Long\"},\"location\":null,\"name\":\"Big Stage Gig\"},{\"address\":\"Gorlizter Park\",\"artist\":{\"email\":\"temple\",\"genre\":\"Rock\",\"gigs\":[],\"name\":\"Temple Haze\"},\"location\":null,\"name\":\"Busking\"},{\"address\":\"Hard Rock 2\",\"artist\":{\"email\":\"will\",\"genre\":\"Singer song writter\",\"gigs\":[],\"name\":\"William Hunt\"},\"location\":null,\"name\":\"Nice songs\"}]").commit();
    }


}
