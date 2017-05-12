package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UnknownApartment extends Activity {

	private String data;
	int count=0;
	private String line1, line2, line3, line4, line5, line6, line7, line8, line9;
	private String react1, react2, react3;
	
	public UnknownApartment() {
		line1="Ah, this seems to be the only unusual frequency with any sort of transmission being sent...";
		line2="Unknown: \"...yes I understand what the consequnces are. I did not expect things to become so out of hand.\"";
		line3="Associate: \"Did your friend from the diner happen to learn the detective's name at least?\"";
		line4="Unknow: \"No, only that he took the travel documents.\"";
		line5="Associate: \"We cannot have one of our own snooping around, we need to find out who he is before it's too late or it's curtains for all of us! Do you understand?!\"";
		react1="One of our own?";
		line6="Unknow: \"Calm down Samuel. I am working on it.\"";
		react2="Samuel? I know a Samuel at the department";
		line7="Associate: \"The boss will not be happy either way. What of the payment?\"";
		line8="Unknown: \"I am not sure. I have not contacted the banker as yet. It should all still be there\"";
		line9="Associate: \"I hope so, for both our sakes\"";
		react3="The transmission just went dead. I need to investigate at the police department";
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unknown_apartment);
		final VaultEngine vE = new VaultEngine();
		
		String d=getIntent().getStringExtra("data");
		data=d;
		
		final DialView dialView = (DialView)findViewById(R.id.radio_dial);
		dialView.setImageResource(R.drawable.radio_dial);
		
		if(!d.contains("shop")) {
			ImageView radio = (ImageView)findViewById(R.id.radio);
			radio.setVisibility(View.INVISIBLE);
			dialView.setVisibility(View.INVISIBLE);
			TextView text = (TextView)findViewById(R.id.radio_instr);
			text.setText(R.string.no_radio);
			Button b=(Button)findViewById(R.id.okay);
			b.setVisibility(View.INVISIBLE);
			Button m=(Button)findViewById(R.id.map);
			m.setVisibility(View.VISIBLE);
			
		}
		dialView.setDialListener(new DialView.DialListener(){
		     @Override
		     public void onDialChanged(int arg) {
		    	 vE.checkCombination(dialView.angle);
		    	 TextView frequency= (TextView)findViewById(R.id.degrees);
		    	 if(vE.getAligned1()) {	 
		    		frequency.setText("000000000");
		    		ImageView pane = (ImageView)findViewById(R.id.prompt_pane);
		    		TextView text = (TextView)findViewById(R.id.radio_instr);
		    		Button listen = (Button)findViewById(R.id.listen);
		    		pane.setVisibility(View.VISIBLE);
		    		text.setVisibility(View.VISIBLE);
		    		text.setText(line1);
		    		listen.setVisibility(View.VISIBLE);
		    	 } else {
		    		 frequency.setText(Integer.toString((int)(Math.random()*890000000+100000000)));
		    	 }	
		     }     
		});
	}

	
	public void closePrompt(View view) {
		ImageView promptPane = (ImageView)findViewById(R.id.prompt_pane);
		promptPane.setVisibility(View.INVISIBLE);
		
		TextView pi = (TextView)findViewById(R.id.radio_instr);
		pi.setVisibility(View.INVISIBLE);
		
		Button okay = (Button)findViewById(R.id.okay);
		okay.setVisibility(View.INVISIBLE);
	}
	
	public void changeDialogue(View view) {
		TextView dialogue = (TextView)findViewById(R.id.radio_instr);
		Button listen = (Button)findViewById(R.id.listen);
		
		if(count==0) {
			dialogue.setText(line2);
			count++;
		} else if(count==1) {
			dialogue.setText(line2);
			count++;
		} else if(count==2) {
			dialogue.setText(line3);
			count++;
		} else if(count==3) {
			dialogue.setText(line4);
			count++;
		} else if(count==4) {
			dialogue.setText(line5);
			listen.setText(react1);
			count++;
		} else if(count==5) {
			dialogue.setText(line6);
			listen.setText(react2);
			count++;
		} else if(count==6) {
			dialogue.setText(line7);
			listen.setText(R.string.listen);
			count++;
		} else if(count==7) {
			dialogue.setText(line8);
			count++;
		} else if(count==8) {
			dialogue.setText(line9);
			listen.setText(react3);
			listen.setTextSize(12);
			count++;
		} else if(count==9) {
			this.finish();
			Intent intent = new Intent(this, OpenMap.class);
			data = data+"unknown";
			intent.putExtra("data", data);
			startActivity(intent);
			
		}
	}
	
	public void openMap(View view) {
		this.finish();
		Intent intent = new Intent(this, OpenMap.class);
		intent.putExtra("data", data);
		startActivity(intent);
	}
}
