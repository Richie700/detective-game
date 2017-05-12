package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HouseVaultContents extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_vault_contents);
	}

	public void closePrompt(View view) {
		ImageView i=(ImageView)findViewById(R.id.prompt_pane);
		TextView t=(TextView)findViewById(R.id.text);
		Button b=(Button)findViewById(R.id.ok);
		Button r=(Button)findViewById(R.id.report);
		i.setVisibility(View.GONE);
		t.setVisibility(View.GONE);
		b.setVisibility(View.GONE);
		r.setVisibility(View.VISIBLE);
	}
	
	public void report(View view) {
		this.finish();
		Intent intent = new Intent(this, Ending.class);
		startActivity(intent);
	}
}
