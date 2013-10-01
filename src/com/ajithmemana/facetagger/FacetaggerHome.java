package com.ajithmemana.facetagger;

import com.qburst.contactlistupdater.ClickFromCamera;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FacetaggerHome extends Activity implements OnClickListener {

	Button cameraButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facetagger_home);
		cameraButton = (Button) findViewById(R.id.cameraButton);
		cameraButton.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_facetagger_home, menu);
		return true;
	}
	public void onClick(View v) {
		// Capture Photo
		/*if (v.getId() == R.id.cameraButton) {
			Intent intent = new Intent(this, ClickFromCamera.class);
			final int ACTIVITY_SELECT_CAMERA_IMAGE = 1234;
			startActivityForResult(intent, ACTIVITY_SELECT_CAMERA_IMAGE);

		}*/
	}

}
