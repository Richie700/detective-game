package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PostLocker extends Activity {
	
	//game data
	private String data;
	//soundpool to play sounds
	private SoundPool sp;
	private int click;
	private int unlock;
	boolean clicked1,clicked2,clicked3,clicked4=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_locker);
		//get data from previous activities
		String d=getIntent().getStringExtra("data");
		data=d;	
		
		//check if been to diner to determine if display instructions or not
		if(d.contains("diner")) {
			ImageView i=(ImageView)findViewById(R.id.prompt_pane);
			TextView t=(TextView)findViewById(R.id.dial_instr);
			Button b=(Button)findViewById(R.id.dial_ok);
			i.setVisibility(View.GONE);
			t.setVisibility(View.GONE);
			b.setVisibility(View.GONE);
		}
		
		//initialise soundpool
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
		//define sounds
		click = sp.load(this, R.raw.tick_sound, 1);
		unlock = sp.load(this, R.raw.unlock, 1);
		
		//create engine for dial on this activity
		final VaultEngine vE = new VaultEngine();
		
		//load the view to run alignment checks
		final DialView dialView = (DialView)findViewById(R.id.postlocker_dial);
		dialView.setDialListener(new DialView.DialListener(){
		     @Override
		     public void onDialChanged(int arg) {
		    	 //load relevant views for alerting player
		    	 TextView a= (TextView)findViewById(R.id.align);
		    	 Button b= (Button)findViewById(R.id.open_postlocker);
		    	 
		    	 //use engine's check function to check alignment of pins
		    	 vE.checkCombination(dialView.angle);
		    	 if(vE.getAligned1()) {	 
		    		 //display alignment for player
		    		 a.setText("First aligned");
		    		 if(!clicked1) {
		    			 //play sound if aligned
		    			 sp.play(click, 1, 1, 0, 0, 1);
		    			 clicked1=true;
		    		 }
		    	 }
		    	 if(vE.getAligned2()) {
		    		 a.setText("Second aligned");
		    		 if(!clicked2) {
		    			 sp.play(click, 1, 1, 0, 0, 1);
		    			 clicked2=true;
		    		 }
		    	 }
		    	 if(vE.getAligned3()) {
		    		 a.setText("Third aligned");
		    		 if(!clicked3) {
		    			 sp.play(click, 1, 1, 0, 0, 1);
		    			 clicked3=true;
		    		 }
		    	 }
		    	 if(vE.getAligned4()) {
		    		 //unlock if all 4 pins aligned
		    		 a.setText("Unlocked");
		    		 if(!clicked4) {
		    			 //unlock sound
		    			 sp.play(unlock, 1, 1, 0, 0, 1);
		    			 clicked4=true;
		    			 b.setVisibility(View.VISIBLE);
		    		 }
		    	 }
		     }     
		});
	}	
	
	//close instructions if displayed
	public void closePrompt(View view) {
		ImageView i=(ImageView)findViewById(R.id.prompt_pane);
		TextView t=(TextView)findViewById(R.id.dial_instr);
		Button b=(Button)findViewById(R.id.dial_ok);
		i.setVisibility(View.GONE);
		t.setVisibility(View.GONE);
		b.setVisibility(View.GONE);
	}
	
	//open locker contents, next mini game
	public void viewContents(View view) {
		this.finish();
		Intent intent = new Intent(this, PostLockerContents.class);
		intent.putExtra("data", data);
		startActivity(intent);
	}
}
