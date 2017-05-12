package com.example.detectivegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class VaultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vault);
		final VaultEngine vE = new VaultEngine();
		TextView num= (TextView)findViewById(R.id.numbers);
		num.setText(vE.getNumber1()+"|"+vE.getRevs1()+", "+
		vE.getNumber2()+"|"+vE.getRevs2()+", "+
		vE.getNumber3()+"|"+vE.getRevs3()+", "+
		vE.getNumber4()+"|"+vE.getRevs4());
		
		final DialView dialView = (DialView)findViewById(R.id.vault_dial);
		dialView.setImageResource(R.drawable.radio_dial);
		dialView.setDialListener(new DialView.DialListener(){
		     @Override
		     public void onDialChanged(int arg) {
		  
		    	 TextView a= (TextView)findViewById(R.id.align);
		    	 vE.checkCombination(dialView.angle);
		    	 if(vE.getAligned1()) {	 
		    		 a.setText("First aligned");
		    	 }
		    	 if(vE.getAligned2()) {
		    		 a.setText("Second aligned");
		    	 }
		    	 if(vE.getAligned3()) {
		    		 a.setText("Third aligned");
		    	 }
		    	 if(vE.getAligned4()) {
		    		 a.setText("Unlocked");
		    	 }
		    	 
		    	 TextView dir= (TextView)findViewById(R.id.degrees);
		    	 if (arg > 0) {
		    		 dir.setText(Float.toString(dialView.angle));
		    	 } else {
		    		 dir.setText(Float.toString(dialView.angle));
		    	 }
		     }     
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vault, menu);
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
}
