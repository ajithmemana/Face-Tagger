package com.ajithmemana.facetagger;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FacetaggerHome extends Activity implements OnClickListener {

	Button cameraButton;
	Button galleryButton;
	private static final int PICK_FROM_CAMERA = 11;
	private static final int PICK_FROM_FILE = 101;

	private Uri photoUri;
	Bitmap photoBitmap;
	int photoHeight;
	int photoWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facetagger_home);
		cameraButton = (Button) findViewById(R.id.cameraButton);
		cameraButton.setOnClickListener(this);
		galleryButton = (Button) findViewById(R.id.galleryButton);
		galleryButton.setOnClickListener(this);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_facetagger_home, menu);
		return true;
	}
	public void onClick(View v) {
		if (v.getId() == R.id.cameraButton)
			loadImageFromCamera();
		else if (v.getId() == R.id.galleryButton)
			// generateFaces();
			loadImageFromGallery();
	}
	private void loadImageFromCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		/*File file = new File(Environment.getExternalStorageDirectory(), "Capture_"
				+ String.valueOf(System.currentTimeMillis()) + ".jpg");
		photoUri = Uri.fromFile(file);
		try {
			intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
			intent.putExtra("return-data", true);*/
			startActivityForResult(intent, PICK_FROM_CAMERA);
		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	private void loadImageFromGallery() {
		Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		i.setType("image/*");
		startActivityForResult(i, PICK_FROM_FILE);
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK && data!= null)
			generateFaces((Bitmap) data.getExtras().get("data"));
		//	Toast.makeText(getBaseContext(), "Camera Photo captured", 0).show();
		else if (requestCode == PICK_FROM_FILE && resultCode == RESULT_OK && data != null) {
			{
				Bitmap b = null;
				try {
					b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				generateFaces(b);
			}

		}

		// TODO handle tomorrow

	}
	public void generateFaces(Bitmap b) {
		//Bitmap b = BitmapFactory.decodeFile(uri.getPath());
		photoBitmap = b.copy(Bitmap.Config.RGB_565, true);
		b.recycle();

		photoHeight = photoBitmap.getHeight();
		photoWidth = photoBitmap.getWidth();
		FaceDetector fd;
		// Stores an array of 10 faces
		Face[] faces = new FaceDetector.Face[10];
		PointF midPoint = new PointF();
		int fPx[] = null;
		int fPy[] = null;
		int count = 0;
		// Detect faces
		fd = new FaceDetector(photoWidth, photoHeight, 10);
		count = fd.findFaces(photoBitmap, faces);
		Toast.makeText(getBaseContext(), count + " faces found", 0).show();

	}

}
