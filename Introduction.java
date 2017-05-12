package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Introduction extends Activity {

	//keep count of the dialogue to order conversations
	private int count=0;
	private String line1, line2, line3, reply1, reply2, reply3;
	
	
	//initialize dialogue between character and player
	public Introduction() {
		line1 ="Head Detective: \"Ah, glad you could make it. I thought that you should have a look at this case, as I gathered it might require someone of...your expertise.";
		reply1="Very well. What happened here?";
		
		line2= "Head Detective: \"An adult male was found to be murdered. Clean shot to the chest...point blank. Looks like a hit, if you ask me.\"";
		reply2="Where is the body?";
		
		line3="Head Detective: \"He has already been sent to the morgue. However, you should poke around the scene of the crime. There may be leads we have overlooked.\"";
		reply3="Okay. I'll do that";
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introduction);
		//set first messages to display
		TextView dialogue = (TextView) findViewById(R.id.detective_dialogue);
		Button response = (Button) findViewById(R.id.response);
		dialogue.setText(line1);
		response.setText(reply1);
	}

	//change dialogue as player clicks button
	public void changeDialogue(View view) {
		TextView dialogue = (TextView) findViewById(R.id.detective_dialogue);
		Button response = (Button) findViewById(R.id.response);
		if(count==0){
			dialogue.setText(line2);
			response.setText(reply2);
			count++;
		} else if(count==1) {
			dialogue.setText(line3);
			response.setText(reply3);
			count++;
		} else if(count==2) {
			this.finish();
			Intent intent = new Intent(this, CrimeScene.class);
			startActivity(intent);
		}
	}
	
}
