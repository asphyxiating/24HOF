package com.twentyfourhof.app.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.inject.Inject;
import com.twentyfourhof.app.R;
import com.twentyfourhof.app.coms.ServerComClass;
import com.twentyfourhof.app.data.MediaItem;
import com.twentyfourhof.app.data.User;
import com.twentyfourhof.app.data.persistence.MediaItemPersister;
import com.twentyfourhof.app.data.persistence.UserPersister;
import com.twentyfourhof.app.ui.events.CustomOnItemSelectedListener;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.add_item_activity)
public class AddItemActivity extends RoboActivity {

    @Inject
    private UserPersister userPersister;
    @Inject
    private MediaItemPersister itemPersister;
    @InjectView(R.id.nameTextView)
    private TextView nameTv;
    @InjectView(R.id.nameEditText)
    private EditText nameEditText;
    @InjectView(R.id.step1TextView)
    private TextView step1Tv;
    @InjectView(R.id.step2TextView)
    private TextView step2Tv;
    @InjectView(R.id.step3TextView)
    private TextView step3Tv;
    @InjectView(R.id.step4TextView)
    private TextView step4Tv;
    @InjectView(R.id.textEditText)
    private EditText textEditText;
    @InjectView(R.id.category_spinner)
    private Spinner spinner;

    private User user;
    @Inject
    private ServerComClass serverComClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            user = userPersister.get();
            nameTv.setText(user.getName());
        } catch (IOException e) {
            Toast.makeText(this, "You must register as a user first", Toast.LENGTH_LONG).show();
        }
        // Font path
        String fontPath = "fonts/max_handwriting.ttf";

        // Loading font face
        Typeface tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        // Applying font
        step1Tv.setTypeface(tf);
        step2Tv.setTypeface(tf);
        step3Tv.setTypeface(tf);
        step4Tv.setTypeface(tf);
        nameTv.setTypeface(tf);

        // Spinner drop-down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Short Story");
        categories.add("Poetry");
        categories.add("Lyrics");
        categories.add("Comedy");
        categories.add("Miscellaneous");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categoryAdapter);

        // Custom spinner item selection listener
        addListenerOnSpinnerItemSelection();

    }

    // Add spinner data

    public void addListenerOnSpinnerItemSelection(){

        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void saveOnClick(View view) {
        MediaItem item = new MediaItem ();
        item.setUser(user);
        item.setName(nameEditText.getText().toString());
        item.setText(textEditText.getText().toString());
        item.setTimeStamp(System.currentTimeMillis() + 172800000); // plus 2 days
        serverComClass.saveItem(item);
        try {
            itemPersister.add(item);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
