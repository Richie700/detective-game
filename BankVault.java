package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BankVault extends Activity {

	private String data;
	private SoundPool sp;
	private int click;
	private int unlock;
	boolean clicked1,clicked2,clicked3,clicked4=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_vault);
		
		String d=getIntent().getStringExtra("data");
		data=d;
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
		click = sp.load(this, R.raw.tick_sound, 1);
		unlock = sp.load(this, R.raw.unlock, 1);
		
		final VaultEngine vE = new VaultEngine();
		final DialView dialView = (DialView)findViewById(R.id.bank_vault_dial);
		dialView.setDialListener(new DialView.DialListener(){
		     @Override
		     public void onDialChanged(int arg) {
		  
		    	 TextView a= (TextView)findViewById(R.id.align);
		    	 Button b= (Button)findViewById(R.id.open_bank_vault);
		    	 
		    	 vE.checkCombination(dialView.angle);
		    	 if(vE.getAligned1()) {	 
		    		 a.setText("First aligned");
		    		 if(!clicked1) {
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
		    		 a.setText("Unlocked");
		    		 if(!clicked4) {
		    			 sp.play(unlock, 1, 1, 0, 0, 1);
		    			 clicked4=true;
		    			 b.setVisibility(View.VISIBLE);
		    		 }
		    	 }
		     }     
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bank_vault, menu);
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
	
	public void viewContents(View view) {
		ImageView p = (ImageView)findViewById(R.id.money);
		Button b = (Button)findViewById(R.id.proceed);
		Button o = (Button)findViewById(R.id.open_bank_vault);
		DialView d=(DialView)findViewById(R.id.bank_vault_dial);
		TextView a = (TextView)findViewById(R.id.align);
		
		p.setVisibility(View.VISIBLE);
		b.setVisibility(View.VISIBLE);
		d.setVisibility(View.INVISIBLE);
		o.setVisibility(View.INVISIBLE);
		a.setVisibility(View.INVISIBLE);
	}
	
	public void openMap(View view) {
		this.finish();
		Intent intent = new Intent(this, OpenMap.class);
		data = data+"bank";
		intent.putExtra("data", data);
		startActivity(intent);
	}
}
