package com.ajithmemana.facetagger;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FacetaggerHome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facetagger_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_facetagger_home, menu);
		return true;
	}

}
