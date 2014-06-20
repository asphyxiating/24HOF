package com.twentyfourhof.app.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.twentyfourhof.app.R;
import com.twentyfourhof.app.data.MediaItem;

import java.util.List;

public class TextAdapter extends ArrayAdapter<MediaItem> {

    public TextAdapter(Context context, int resource, List<MediaItem> objects) {
        super(context, resource, objects); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout relLayout = (RelativeLayout) inflater.inflate(R.layout.item_in_listview, null);
        MediaItem item = getItem(position);
        TextView titleTv = (TextView) relLayout.findViewById(R.id.title);
        TextView textTv = (TextView) relLayout.findViewById(R.id.text);
        titleTv.setText(item.getName());
        textTv.setText(item.getText());
        return relLayout;
    }
}