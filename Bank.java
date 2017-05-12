package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bank extends Activity {

	private String data;
	private int count=0;
	private String line1, line2, line3, line4;
	private String reply1, reply2, reply3, reply4;
	
	
	public Bank() {
		line1="Banker: \"Good day sir, how may I help you?\"";
		reply1="May I speak to William Hill please?";
		
		line2="Banker: \"That would be me, sir. Do you have an account here, may I enquire?\"";
		reply2="I do...";
		
		line3="Banker: \"If you will, sir, would you please provide your full name and account number?\"";
		reply3="My account number is 5484095516...";
		
		line4="Banker: \"Ah, indeed. Follow me. I trust you know the combination...\"";
		reply4="Thank you";
	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank);
		String d=getIntent().getStringExtra("data");
		data=d;
		
		TextView dialogue = (TextView) findViewById(R.id.bank_dialogue);
		Button response = (Button) findViewById(R.id.bank_response);
		dialogue.setText(line1);
		response.setText(reply1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bank, menu);
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
		TextView dialogue = (TextView) findViewById(R.id.bank_dialogue);
		Button response = (Button) findViewById(R.id.bank_response);
		if(count==0){
			dialogue.setText(line2);
			response.setText(reply2);
			count++;
			
			if(!data.contains("post")){
				response.setText("I do not");
				line3="Banker: \"In that case I'm afraid I cannot help you.\"";
				reply3="Okay";
			}
		} else if(count==1) {
			dialogue.setText(line3);
			response.setText(reply3);
			count++;
			if(!data.contains("post")){
				count=4;
			}
		} else if(count==2) {
			dialogue.setText(line4);
			response.setText(reply4);
			count++;
		} else if(count==3) {
			this.finish();
			Intent intent = new Intent(this, BankVault.class);
			intent.putExtra("data",data);
			startActivity(intent);
		} else if (count==4) {
			this.finish();
			Intent intent = new Intent(this, OpenMap.class);
			intent.putExtra("data",data);
			startActivity(intent);
		}
	}
}
