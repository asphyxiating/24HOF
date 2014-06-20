package com.twentyfourhof.app.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
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

        final MediaItem item = getItem(position);

        TextView titleTv = (TextView) relLayout.findViewById(R.id.title);
        TextView textTv = (TextView) relLayout.findViewById(R.id.text);
        TextView categoryTv = (TextView) relLayout.findViewById(R.id.categoryTextView);

        CheckBox currentItemFavorised = (CheckBox)relLayout.findViewById(R.id.favorizebutton);
        currentItemFavorised.setChecked(item.isFavorised());

        currentItemFavorised.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isFavorised) {
                item.setFavorised(isFavorised);
            }
        });

        /*
        currentItemFavorised.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean isFavorised) {

                if (isFavorised == true) {
                    item.setFavorised(isFavorised);

                } else {

                }
            }
        });

        final Button currentItemFavorised = (Button)relLayout.findViewById(R.id.favorizebutton);
        currentItemFavorised.setFocusable(item.isFavorised());
        currentItemFavorised.setFocusableInTouchMode(true);
        currentItemFavorised.requestFocus();
        */

        titleTv.setText(item.getName());
        textTv.setText(item.getText());
        categoryTv.setText(item.getCategory());
        return relLayout;
    }
}