package com.twentyfourhof.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.twentyfourhof.app.R;

import java.io.InputStream;

public class UploadFragment extends Fragment {

	private static final int PIC_REQUEST_CODE = 1;
	// Buttons used
	ImageButton audioCaptureButton;
	ImageButton videoCaptureButton;
	ImageButton textCaputureButton;
	ImageButton pictureCaptureButton;
	ImageView picturePreview;
	Context context;
	Bitmap mBitmap;
	String TAG;

	public void Context(Context myContext) {
		context = myContext;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_upload, container,
				false);

		TAG = "Upload Fragment";
		
		
		pictureCaptureButton = (ImageButton) rootView
				.findViewById(R.id.CameraButton);
		
		picturePreview = (ImageView) rootView.findViewById(R.id.PicPreview);

		pictureCaptureButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("pictureCapture", "onClickListener ist gestartet");
				// open Gallery to upload picture
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(
						Intent.createChooser(intent, "Select..."),
						PIC_REQUEST_CODE);
			}
		});

		return rootView;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PIC_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			Uri uri = data.getData();
			Toast.makeText(context.getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
			InputStream stream;
			try {
				stream = context.getContentResolver().openInputStream(
						uri);
				mBitmap = BitmapFactory.decodeStream(stream);
				picturePreview.setImageBitmap(mBitmap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, "Decoding Bitmap",e);
			}
			
		}

	}

}
