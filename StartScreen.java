package com.example.detectivegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class StartScreen extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
	}

	public void openMap(View view) {
		Intent intent = new Intent(this, OpenMap.class);
		startActivity(intent);
	}
	
	
	public void openMurderScene(View view) {
		Intent intent = new Intent(this, Introduction.class);
		startActivity(intent);

	}
}
