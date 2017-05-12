package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PoliceLockerContents extends Activity {
	
	private String data;
	private String hiddenMessage ="MTPWTAUDMTNT";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_police_locker_contents);
		String d=getIntent().getStringExtra("data");
		data=d;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.police_locker_contents, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void checkGuess(View view) {
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
			
			TextView number = (TextView) findViewById(R.id.message);
			number.setVisibility(View.VISIBLE);
			
			ImageView acc=(ImageView) findViewById(R.id.paper);
			acc.setVisibility(View.VISIBLE);
			
		}else {
			alert.setText("Nothing meaningful can be deciphered, try a different combination");
			alert.setTextColor(Color.RED);
		}	
	}
	
	public void openMap(View view) {
		this.finish();
		Intent intent = new Intent(this, OpenMap.class);
		data = data+"police";
		intent.putExtra("data",data);
		startActivity(intent);
		
	}
}
