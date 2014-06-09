package com.twentyfourhof.app.ui;

/**
 * Created by kannzaubern on 22.05.14.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.google.inject.Inject;
import com.twentyfourhof.app.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.start_activity)
public class StartActivity extends RoboActivity {

    private static final String SEEN = "seen";
    @Inject
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (sharedPreferences.getBoolean(SEEN, false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


    public void signupOnClick(View view) {
        sharedPreferences.edit().putBoolean(SEEN, true).commit();
        startActivity(new Intent(this, EditProfile.class));
        finish();
    }
}
