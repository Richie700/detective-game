package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PostOffice extends Activity {

	//game data
	private String data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_office);
		//get game data from previous activities
		String d=getIntent().getStringExtra("data");
		data=d;
	}
	
	public void openLocker(View view) {
		this.finish();
		Intent intent = new Intent(this, PostLocker.class);
		intent.putExtra("data", data);
		startActivity(intent);
	}
	
	public void closePrompt(View view) {
		ImageView i=(ImageView)findViewById(R.id.prompt_pane);
		TextView t=(TextView)findViewById(R.id.instr);
		Button b=(Button)findViewById(R.id.okay	);
		i.setVisibility(View.GONE);
		t.setVisibility(View.GONE);
		b.setVisibility(View.GONE);
	}
}
