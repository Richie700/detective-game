package com.example.detectivegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OpenMap extends ActionBarActivity {

	private String data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_map);
		String d=getIntent().getStringExtra("data");
		data=d;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_map, menu);
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
	
	public void openPost(View view) {
		if(!data.contains("post")){
			this.finish();
			Intent intent = new Intent(this, PostOffice.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else {
			openPrompt(view);
		}
	}
	
	public void openDiner(View view) {
		if(!data.contains("diner")){
			this.finish();
			Intent intent = new Intent(this, Diner.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else {
			openPrompt(view);
		}
	}
	
	public void openPolice(View view) {
		if(!data.contains("police")) {
			this.finish();
			Intent intent = new Intent(this, PoliceDepartment.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else {
			openPrompt(view);
		}
	}
	
	public void openDinerApartment(View view) {
		if(data.contains("diner") && !data.contains("phonetap")) {
			this.finish();
			Intent intent = new Intent(this, DinerOwnerApartment.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else {
			openPrompt(view);
		}
	}
	
	public void openUnknownApartment(View view) {
		if(data.contains("phonetap") && !data.contains("unknown")) {
			this.finish();
			Intent intent = new Intent(this, UnknownApartment.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else {
			openPrompt(view);
		}
	}
	
	public void openBank(View view) {
		if(!data.contains("bank")) {
			this.finish();
			Intent intent = new Intent(this, Bank.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else {
			openPrompt(view);
		}
	}
	
	public void openHouse(View view) {
		if(data.contains("police")){
			this.finish();
			Intent intent = new Intent(this, House.class);
			intent.putExtra("data", data);
			startActivity(intent);
		}	else {
			openPrompt(view);
		}
	}
	
	public void openShop(View view) {
		if(!data.contains("shop")) {
			this.finish();
			Intent intent = new Intent(this, Shop.class);
			intent.putExtra("data", data);
			startActivity(intent);
		} else {
			openPrompt(view);
		}
	}
		
	public void closePrompt(View view) {
		ImageView i=(ImageView)findViewById(R.id.prompt_pane);
		TextView t=(TextView)findViewById(R.id.message);
		Button b=(Button)findViewById(R.id.okay);
		i.setVisibility(View.GONE);
		t.setVisibility(View.GONE);
		b.setVisibility(View.GONE);
	}
	
	public void openPrompt(View view) {
		ImageView i=(ImageView)findViewById(R.id.prompt_pane);
		TextView t=(TextView)findViewById(R.id.message);
		Button b=(Button)findViewById(R.id.okay);
		i.setVisibility(View.VISIBLE);
		t.setVisibility(View.VISIBLE);
		b.setVisibility(View.VISIBLE);
	}
	
	
}
