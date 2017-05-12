package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ending extends Activity {

	private int count=0;
	private String line1, line2, line3, line4; 
	private String reply1, reply2, reply3, reply4;
	
	
	public Ending() {
		line1="Head Detective: \"Congratulations detective, the governor and his associates are behind bars, but I am afraid that it is bittersweet. While not finding the actual killer we have stumbled upon an even more ominous finding.\"";
		reply1="What do you mean?";
		line2="Head Detective: \"Well, you see, the Soviet spies are even more deep seated than previously anticipated. This was but a glimpse of what they are truly capable of.\"";
		reply2="What are we expecting?";
		line3="Head Detective: \"No one wants to say, but we seem to be on the brink of a war, not a guns-and-soldiers war, mind you...an intelligence war.\"";
		reply3="That is going to be a problem...";
		line4="Head Detective: \"Which is why I am promoting you to Senior Detective. We need more people with your...particular set of skills.\"";
		reply4="Thank you, sir. I look forward to this opportunity...";
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ending);
		
		TextView dialogue = (TextView) findViewById(R.id.detective_dialogue);
		Button response = (Button) findViewById(R.id.response);
		dialogue.setText(line1);
		response.setText(reply1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ending, menu);
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
	
	public void changeDialogue(View view) {
		TextView dialogue = (TextView) findViewById(R.id.detective_dialogue);
		Button response = (Button) findViewById(R.id.response);
		if(count==0){
			dialogue.setText(line2);
			response.setText(reply2);
			count++;
		} else if(count==1){
			dialogue.setText(line3);
			response.setText(reply3);
			count++;
		} else if(count==2){
			dialogue.setText(line4);
			response.setText(reply4);
			count++;
		} else if(count==3) {
			this.finish();
			Intent intent = new Intent(this, StartScreen.class);
			startActivity(intent);
		}
	}
}
