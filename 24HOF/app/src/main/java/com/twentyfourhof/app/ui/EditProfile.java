package com.twentyfourhof.app.ui;

/**
 * Created by kannzaubern on 22.05.14.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.twentyfourhof.app.R;
import com.twentyfourhof.app.data.User;
import com.twentyfourhof.app.data.UserImages;
import com.twentyfourhof.app.data.persistence.UserPersister;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


@ContentView(R.layout.edit_profile)
public class EditProfile extends RoboActivity {

    public static final int REQUEST_CODE = 1;

    @InjectView(R.id.nameEditText)
    private EditText nameEditText;
    @InjectView(R.id.emailEditText)
    private EditText emailEditText;
    @InjectView(R.id.ageEditText)
    private EditText ageEditText;
    @InjectView(R.id.pictureIv)
    private ImageView pictureTv;

    @Inject
    private UserPersister userPersister;
    private Bitmap mBitmap;
    // private File photoFile = null;


    public void saveOnClick(View view) {
        User user = new User();
        user.setName(nameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user.setAge(ageEditText.getText().toString());
    //    user.setImagePath(photoFile.getAbsolutePath());

        try {
            userPersister.save(user);
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("isUser", true);
            startActivity(intent);
            finish();
        } catch (JsonProcessingException e) {
            Toast.makeText(this, "Unable to save", Toast.LENGTH_LONG).show();
        }
    }


    public void pictureOnClick(View view) {
        Intent takePictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
        takePictureIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(takePictureIntent, "Select image..."), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
          /* if(photoFile.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                pictureTv.setImageBitmap(bitmap);
            }
            Log.d(EditProfile.class.getSimpleName(), "exists" + photoFile.exists());*/
        }

        Uri uri = data.getData();
        InputStream stream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            stream = getContentResolver().openInputStream(uri);
            mBitmap = BitmapFactory.decodeStream(stream);
            if (mBitmap != null) {

                mBitmap
                        .compress(
                                Bitmap.CompressFormat.JPEG,
                                UserImages.KEY_PROFILE_PIC_COMPRESS_FACTOR,
                                byteArrayOutputStream);
                // photoFile = createImageFile();
            }
            pictureTv.setImageBitmap(mBitmap);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to upload picture", Toast.LENGTH_LONG).show();
        }

    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

}

