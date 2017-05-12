package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CrimeScene extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crime_scene);
	}
	
	//provide output on objects touched
	public void analyse1(View view) {
		TextView hint = (TextView) findViewById(R.id.clue_hint);
		hint.setText("The body has been taken away, there is nothing left to analyse");
	}
	
	public void analyse2(View view) {
		TextView hint = (TextView) findViewById(R.id.clue_hint);
		hint.setText("The victim seems to have enjoyed Russian literature");
	}
	
	public void analyse3(View view) {
		TextView hint = (TextView) findViewById(R.id.clue_hint);
		hint.setText("Maps of North America and Soviet Russia");
	}
	
	//open first deciphering mini game
	public void openNewspaper(View view) {
		this.finish();
		Intent intent = new Intent(this, Newspaper.class);
		startActivity(intent);
	}
}
