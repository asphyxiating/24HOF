package com.twentyfourhof.app.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.twentyfourhof.app.R;
import com.twentyfourhof.app.data.MediaItem;
import roboguice.util.RoboAsyncTask;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

public class MediaItemAdapter extends ArrayAdapter<MediaItem> {

    private Calendar calendar = Calendar.getInstance();

    public MediaItemAdapter(Context context, int resource, List<MediaItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.media_item, null);
        TextView nameTextView = (TextView) linearLayout.findViewById(R.id.titleTextView);
        TextView textTextView = (TextView) linearLayout.findViewById(R.id.textTextView);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.photoIv);

        MediaItem item = getItem(position);
        nameTextView.setText(item.getUser().getName() + " - " + item.getName());
        calendar.setTimeInMillis(item.getTimeStamp());
        textTextView.setText(item.getText());
        new ImageTask(getContext(), item.getPictureURL(), imageView).execute();
        return linearLayout;
    }
    class ImageTask extends RoboAsyncTask<Bitmap> {

        private String urlString = "";
        private ImageView imageView;

        protected ImageTask(Context context, String url, ImageView imageView) {
            super(context);
            this.urlString = url;
            this.imageView = imageView;
        }

        @Override
        public Bitmap call() throws Exception {
            URL url = new URL(urlString);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }

        @Override
        protected void onSuccess(Bitmap bitmap) throws Exception {
            super.onSuccess(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}

