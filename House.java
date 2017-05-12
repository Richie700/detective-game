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

public class House extends Activity {

	private SoundPool sp;
	private int click;
	private int unlock;
	boolean clicked1,clicked2,clicked3,clicked4=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house);
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
		click = sp.load(this, R.raw.tick_sound, 1);
		unlock = sp.load(this, R.raw.unlock, 1);
		
		final VaultEngine vE = new VaultEngine();
		
		final DialView dialView = (DialView)findViewById(R.id.vault_dial);
		dialView.setDialListener(new DialView.DialListener(){
		     @Override
		     public void onDialChanged(int arg) {
		    	 
		    	 vE.checkCombination(dialView.angle);
		    	 if(vE.getAligned1()) {	 
		    		 if(!clicked1) {
		    			 sp.play(click, 1, 1, 0, 0, 1);
		    			 clicked1=true;
		    		 }
		    	 }
		    	 if(vE.getAligned2()) {
		    		 if(!clicked2) {
		    			 sp.play(click, 1, 1, 0, 0, 1);
		    			 clicked2=true;
		    		 }
		    	 }
		    	 if(vE.getAligned3()) {
		    		 if(!clicked3) {
		    			 sp.play(click, 1, 1, 0, 0, 1);
		    			 clicked3=true;
		    		 }
		    	 }
		    	 if(vE.getAligned4()) {
		    		 if(!clicked4) {
		    			 sp.play(unlock, 1, 1, 0, 0, 1);
		    			 clicked4=true;
		    			 Button b= (Button)findViewById(R.id.open_vault);
		    			 b.setVisibility(View.VISIBLE);
		    		 }
		    	 }
		     }     
		});
	}

	public void closePrompt(View view) {
		ImageView i=(ImageView)findViewById(R.id.prompt_pane);
		TextView t=(TextView)findViewById(R.id.dial_instr);
		Button b=(Button)findViewById(R.id.dial_ok);
		i.setVisibility(View.GONE);
		t.setVisibility(View.GONE);
		b.setVisibility(View.GONE);
	}
	
	public void viewContents(View view) {
		this.finish();
		Intent intent = new Intent(this, HouseVaultContents.class);
		startActivity(intent);
	}
}
