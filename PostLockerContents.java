package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PostLockerContents extends Activity {
	
	//game data
	private String data;
	private String hiddenMessage ="EDRNJSEEAF";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_locker_contents);
		//get game data
		String d=getIntent().getStringExtra("data");
		data=d;
	}
	
	public void checkGuess(View view) {
		//same as newspaper mini game except words are embedded in image itself, a.k.a the background images
		TextView alert = (TextView) findViewById(R.id.validity_alert);
		EditText guess = (EditText) findViewById(R.id.message_guess);
		
		String check = guess.getText().toString();
		check=check.replaceAll(" ", "");
		
		if(check.equalsIgnoreCase(hiddenMessage)){
			alert.setVisibility(View.INVISIBLE);
			guess.setVisibility(View.INVISIBLE);
			Button decipher=(Button) findViewById(R.id.decipher_button);
			decipher.setVisibility(View.INVISIBLE);
			
			Button okay=(Button) findViewById(R.id.okay);
			okay.setVisibility(View.VISIBLE);
			
			TextView number = (TextView) findViewById(R.id.number);
			number.setVisibility(View.VISIBLE);
			
			ImageView acc=(ImageView) findViewById(R.id.account_number);
			acc.setVisibility(View.VISIBLE);
			
		}else {
			alert.setText("Nothing meaningful can be deciphered, try a different combination");
			alert.setTextColor(Color.RED);
		}	
	}
	
	//open map
	public void openMap(View view) {
		this.finish();
		Intent intent = new Intent(this, OpenMap.class);
		//update game data, been to post office
		data = data+"post";
		intent.putExtra("data",data);
		startActivity(intent);	
	}
}
