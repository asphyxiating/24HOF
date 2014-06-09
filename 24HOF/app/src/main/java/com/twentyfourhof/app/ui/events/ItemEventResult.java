package com.twentyfourhof.app.ui.events;

import com.twentyfourhof.app.data.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class ItemEventResult {

    private List<MediaItem> items = new ArrayList<MediaItem>();

    public ItemEventResult(List<MediaItem> items) {
        this.items = items;
    }

    public List<MediaItem> getItems() {
        return items;
    }

    public void setItems(List<MediaItem> items) {
        this.items = items;
    }
}
