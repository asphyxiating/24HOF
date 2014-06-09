package com.twentyfourhof.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.inject.Inject;
import com.twentyfourhof.app.R;
import com.twentyfourhof.app.coms.ServerComClass;
import com.twentyfourhof.app.data.MediaItem;
import com.twentyfourhof.app.data.User;
import com.twentyfourhof.app.data.persistence.MediaItemPersister;
import com.twentyfourhof.app.data.persistence.UserPersister;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.io.IOException;

@ContentView(R.layout.add_item_activity)
public class AddItemActivity extends RoboActivity {

    @Inject
    private UserPersister userPersister;
    @Inject
    private MediaItemPersister itemPersister;
    @InjectView(R.id.nameTextView)
    private TextView textView;
    @InjectView(R.id.nameEditText)
    private EditText nameEditText;
    @InjectView(R.id.addressEditText)
    private EditText addressEditText;
    private User user;
    @Inject
    private ServerComClass serverComClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            user = userPersister.get();
            textView.setText(user.getName());
        } catch (IOException e) {
            Toast.makeText(this, "You must register as a user first", Toast.LENGTH_LONG).show();
        }
    }

    public void saveOnClick(View view) {
        MediaItem item = new MediaItem ();
        item.setUser(user);
        item.setName(nameEditText.getText().toString());
        item.setText(addressEditText.getText().toString());
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
