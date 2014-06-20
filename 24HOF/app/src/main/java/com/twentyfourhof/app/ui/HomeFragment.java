package com.twentyfourhof.app.ui;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.twentyfourhof.app.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class HomeFragment extends Fragment {

    private TextView tv;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);

        // Font path
        String fontPath = "fonts/max_handwriting.ttf";

        // get current date and fill it into TextView
        tv = (TextView) rootView.findViewById(R.id.date);
        Time now = new Time();
        now.setToNow();

        String snapshotTime = now.format("%m" + "/" + "%d" + " --- " + "%Y");
        snapshotTime = simpleDateFormat.format(Calendar.getInstance().getTime());
        tv.setText(snapshotTime);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        // Applying font
        tv.setTypeface(tf);

        // audiofile = (MediaPlayer) audiofile.create(this, R.raw.test_song);
        return rootView;
    }

}