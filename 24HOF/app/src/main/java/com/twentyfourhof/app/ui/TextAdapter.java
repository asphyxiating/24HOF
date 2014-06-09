package com.twentyfourhof.app.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.inject.Inject;
import com.twentyfourhof.app.data.MediaItem;
import com.twentyfourhof.app.ui.events.ItemEventResult;
import roboguice.event.Observes;

import java.util.List;

public class TextAdapter extends ArrayAdapter<MediaItem> {

    @Inject
    public TextAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MediaItem mediaItem = getItem(position);


        TextView textView = new TextView(getContext());
        textView.setText(mediaItem.getName() + " " + mediaItem.getText());

        return textView;
    }

    protected void handleGigs(@Observes ItemEventResult itemEventResult) {
        clear();
        List<MediaItem> mediaItems = itemEventResult.getItems();
        addAll(mediaItems);
        notifyDataSetChanged();
    }

}
