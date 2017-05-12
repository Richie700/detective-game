package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PoliceDepartment extends Activity {
	
	private String data;
	private int count=0;
	private String line1, line2, line3, line4;
	private String reply1, reply2, reply3, reply4;
	
	public PoliceDepartment() {
		line1="Officer: \"Good day detective, can I help you?\"";
		reply1="Can I trust you, officer?";
		line2="Officer: \"Of course, detective. I've nothing to hide.\"";
		reply2="I must examine Samuel's locker immediately";
		line3="Officer: \"You will not be able to without his locker combination. Should I call him to open it?\"";
		reply3="No, not at all. He must not know";
		line4="Officer: \"But how will you open it, detective\"?";
		reply4="Trust me, I'm quite good with these";
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_police_department);
		
		String d=getIntent().getStringExtra("data");
		data=d;
		TextView dialogue = (TextView) findViewById(R.id.dialogue);
		Button response = (Button) findViewById(R.id.response);
		if(!data.contains("unknown")) {
			reply1="No, officer. Just here for a visit";
			line2="Officer: \"Why, that's very thoughtful of you, detective. We appreciate it.\"";
			reply2="Farewell";
		}
		dialogue.setText(line1);
		response.setText(reply1);
	}
	
	public void changeDialogue(View view) {
		TextView dialogue = (TextView) findViewById(R.id.dialogue);
		Button response = (Button) findViewById(R.id.response);
		if(count==0){
			dialogue.setText(line2);
			response.setText(reply2);
			count++;
			if(!data.contains("unknown")) {
				count=4;
			}
			
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
			Intent intent = new Intent(this, PoliceLocker.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else if(count==4) {
			this.finish();
			Intent intent = new Intent(this, OpenMap.class);
			intent.putExtra("data", data);
			startActivity(intent);
		}
	}
}
