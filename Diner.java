package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Diner extends Activity {

	//game data
	private String data;
	private int count=0;
	private String line1,line2,line3, line4, line5, line6, line7,line8;
	private String reply1,reply2,reply3,reply4,reply5, reply6, reply7, reply8;
	
	//dialogue
	public Diner() {
		line1="Diner Owner: \"Hello. What do you want?\"";
		reply1="Hm, a pot of coffee perhaps, thank you";
		
		line2="Diner Owner: \"Just take seat, I will bring it to you.\"";
		reply2="Thank you";
		
		line3="Diner Owner: \"Where are you from? I don't see you around here before.\"";
		reply3="I live a little further out. What about yourself?";
		
		line4="Diner Owner: \"I live in apartment across street. If you mean what country, Russia. Been here for twenty years.\"";
		reply4="Did you hear about the murder nearby?";
		
		line5="Diner Owner: \"I did. Very sad. Why do you ask?\"";
		reply5="I was curious";
		
		line6="Diner Owner: \"Hm, okay. Let me get coffee.\"";
		reply6="Very well. Does your restroom require a key?";
		
		line7="Diner Owner: \"Yes it does.\"";
		reply7="May I use it, please?";
		
		line8="Diner Owner: \"Here. Bring it back here when you are done.\"";
		reply8="Won't be a moment";

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diner);
		String d=getIntent().getStringExtra("data");
		data=d;
		
		TextView dialogue = (TextView) findViewById(R.id.dialogue);
		Button response = (Button) findViewById(R.id.diner_response);
		
		dialogue.setText(line1);
		response.setText(reply1);
	}
	
	public void changeDialogue(View view) {
		TextView dialogue = (TextView) findViewById(R.id.dialogue);
		Button response = (Button) findViewById(R.id.diner_response);
		
		if(count==0) {
			dialogue.setText(line2);
			response.setText(reply2);
			count++;
		} else if(count==1) {
			dialogue.setText(line3);
			response.setText(reply3);
			count++;
		} else if(count==2) {
			dialogue.setText(line4);
			response.setText(reply4);
			count++;
		} else if(count==3) {
			dialogue.setText(line5);
			response.setText(reply5);
			count++;
		} else if(count==4) {
			dialogue.setText(line6);
			response.setText(reply6);
			count++;
		} else if(count==5) {
			dialogue.setText(line7);
			response.setText(reply7);
			count++;
		} else if(count==6) {
			dialogue.setText(line8);
			response.setText(reply8);
			count++;
		} else if(count==7) {
			this.finish();
			Intent intent = new Intent(this, DinerVault.class);
			intent.putExtra("data",data);
			startActivity(intent);
		}	
	}
}
