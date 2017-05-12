package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Shop extends Activity{
	
	private String data;
	ImageView transPink;
	ImageView sDiode;
	ImageView transBlue;
	ImageView bDiode;
	int allowance = 60;
	int smallItemAllowance=100;
	float x,y =0.0f;
	boolean pinkPlaced=false;
	boolean bluePlaced=false;
	boolean smallPlaced=false;
	boolean bigPlaced=false;
	boolean moving=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		
		String d=getIntent().getStringExtra("data");
		data=d;
		
		transPink=(ImageView)findViewById(R.id.pink_transistor);
		transPink.setOnTouchListener(new ImageTouchListener(transPink));
	
		transBlue=(ImageView)findViewById(R.id.blue_transistor);
		transBlue.setOnTouchListener(new ImageTouchListener(transBlue));
		
		sDiode=(ImageView)findViewById(R.id.small_diode);
		sDiode.setOnTouchListener(new ImageTouchListener(sDiode));
		
		bDiode=(ImageView)findViewById(R.id.big_diode);
		bDiode.setOnTouchListener(new ImageTouchListener(bDiode));
		
	}
	
	
	private class ImageTouchListener implements OnTouchListener {
		ImageView iv;
		public ImageTouchListener(ImageView iv) {
			this.iv=iv;
		}
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				moving=true;
				break;
			case MotionEvent.ACTION_MOVE:
				if(moving) {
					x=event.getRawX()-iv.getWidth()/2;
					y=event.getRawY()-iv.getHeight()*3/2;
					iv.setX(x);
					iv.setY(y);
				}
				break;
			case MotionEvent.ACTION_UP:
				moving=false;
				break;
			}
			
			checkPlacements(iv);
			
			return true;
		}		
	}

	private void checkPlacements(ImageView iv) {
		
		if(iv.getId()==R.id.pink_transistor) {
			
			ImageView h=(ImageView)findViewById(R.id.pink_holder);
			if((iv.getX()>=h.getX()-allowance && iv.getX()<=h.getX()+allowance) &&
					(iv.getY()>=h.getY()-allowance && iv.getY()<=h.getY()+allowance)) {
				h.setImageResource(R.drawable.greenholder);	
				pinkPlaced=true;
			} else {
				h.setImageResource(R.drawable.redholder);
				pinkPlaced=false;
			}
			
		} else if(iv.getId()==R.id.blue_transistor) {
			
			ImageView h=(ImageView)findViewById(R.id.blue_holder);
			if((iv.getX()>=h.getX()-allowance && iv.getX()<=h.getX()+allowance) &&
					(iv.getY()>=h.getY()-allowance && iv.getY()<=h.getY()+allowance)) {
				h.setImageResource(R.drawable.greenholder);	
				bluePlaced=true;
			} else {
				h.setImageResource(R.drawable.redholder);
				bluePlaced=false;
			}
			
		} else if(iv.getId()==R.id.small_diode) {
			
			ImageView h=(ImageView)findViewById(R.id.small_holder);
			if((iv.getX()>=h.getX()-smallItemAllowance && iv.getX()<=h.getX()+smallItemAllowance) &&
					(iv.getY()>=h.getY()-smallItemAllowance && iv.getY()<=h.getY()+smallItemAllowance)) {
				h.setImageResource(R.drawable.greenholder);	
				smallPlaced=true;
			} else {
				h.setImageResource(R.drawable.redholder);
				smallPlaced=false;
			}
			
		} else if(iv.getId()==R.id.big_diode) {
			
			ImageView h=(ImageView)findViewById(R.id.big_holder);
			if((iv.getX()>=h.getX()-allowance && iv.getX()<=h.getX()+allowance) &&
					(iv.getY()>=h.getY()-allowance && iv.getY()<=h.getY()+allowance)) {
				h.setImageResource(R.drawable.greenholder);	
				bigPlaced=true;
			} else {
				h.setImageResource(R.drawable.redholder);
				bigPlaced=false;
			}
			
		}
		
		makeable(iv);

	}
	
	private void makeable(View view) {		
		if(pinkPlaced && bluePlaced && bigPlaced && smallPlaced) {
			ImageView p = (ImageView)findViewById(R.id.pink_transistor);
			ImageView ph = (ImageView)findViewById(R.id.pink_holder);
			p.setVisibility(View.INVISIBLE);
			ph.setVisibility(View.INVISIBLE);
			
			ImageView b = (ImageView)findViewById(R.id.blue_transistor);
			ImageView bh = (ImageView)findViewById(R.id.blue_holder);
			b.setVisibility(View.INVISIBLE);
			bh.setVisibility(View.INVISIBLE);
			
			ImageView bg = (ImageView)findViewById(R.id.big_diode);
			ImageView bgh = (ImageView)findViewById(R.id.big_holder);
			bg.setVisibility(View.INVISIBLE);
			bgh.setVisibility(View.INVISIBLE);
			
			ImageView s = (ImageView)findViewById(R.id.small_diode);
			ImageView sh = (ImageView)findViewById(R.id.small_holder);
			s.setVisibility(View.INVISIBLE);
			sh.setVisibility(View.INVISIBLE);
			
			ImageView i = (ImageView)findViewById(R.id.prompt_pane);
			i.setVisibility(View.VISIBLE);
			
			TextView r = (TextView)findViewById(R.id.radio_info);
			r.setVisibility(View.VISIBLE);
			
			Button but = (Button)findViewById(R.id.proceed);
			but.setVisibility(View.VISIBLE);
		}
	}
	
	public void openMap(View view) {
		this.finish();
		Intent intent = new Intent(this, OpenMap.class);
		data=data+"shop";
		intent.putExtra("data", data);
		startActivity(intent);
	}
	
	public void closePrompt(View view) {
		ImageView hint = (ImageView) findViewById(R.id.prompt_pane);
		hint.setVisibility(View.GONE);	
		TextView text = (TextView)findViewById(R.id.radio_instr);
		text.setVisibility(View.GONE);
		Button b= (Button)findViewById(R.id.okay);
		b.setVisibility(View.GONE);
	}
}
