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

public class DinerOwnerApartment extends Activity {
	
	private String data;
	
	//allowance for wire placing
	int allowance = 60;
	int adjustment = 200;
	//x,y coordinates
	float x,y =0.0f;
	//wires and placements
	ImageView blue, red, yellow, green;
	boolean bluePlaced, redPlaced, yellowPlaced, greenPlaced = false;
	boolean moving=false;
	
	int count=0;
	private String line1, line2, line3, line4, line5, line6, line7, line8, line9;
	
	public DinerOwnerApartment() {
		line1="There seems to be some activity going on from his phone line...";
		line2="Diner Owner: \"...why did he get rid of him? He was supposed to take information back to Russia next week.\"";
		line3="Unknown: \"We did what we needed to do. He was compromised.\"";
		line4="Diner Owner:\"Well, now there is detective on case too. I did not know until passport and ticket disappeared from my safe.\"";
		line5="Unknown: \"What!? How can you be so careless?!\"";
		line6="Diner Owner: \"I am sorry. I did not realise until too late.\"";
		line7="Unknown: \"You're walking a fine line, my friend. I will clean this mess up. We'll need our inside associates. Luckily for you I live near enough to them for private radio communications.\"";
		line8="Diner Owner: \"What do I do now?\"";
		line9="Unknow: \"Nothing *click*\"";

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diner_owner_apartment);
		
		//add touch listeners to wires
		String d=getIntent().getStringExtra("data");
		data=d;
		
		blue=(ImageView)findViewById(R.id.blue_wire);
		blue.setOnTouchListener(new ImageTouchListener(blue));
		
		red=(ImageView)findViewById(R.id.red_wire);
		red.setOnTouchListener(new ImageTouchListener(red));
		
		yellow=(ImageView)findViewById(R.id.yellow_wire);
		yellow.setOnTouchListener(new ImageTouchListener(yellow));
		
		green=(ImageView)findViewById(R.id.green_wire);
		green.setOnTouchListener(new ImageTouchListener(green));
	}
	
	private class ImageTouchListener implements OnTouchListener {
		//determine which wire is being moved
		ImageView iv;
		public ImageTouchListener(ImageView iv) {
			this.iv=iv;
		}
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//detect movement
				moving=true;
				break;
			case MotionEvent.ACTION_MOVE:
				//if moving, calculate new coordinates and draw in new position
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
		
		//check if placed in correct positions, predefined. 
		if(iv.getId()==R.id.blue_wire) {
			
			ImageView h=(ImageView)findViewById(R.id.blue_holder);
			if((iv.getX()>=h.getX()-allowance && iv.getX()<=h.getX()+allowance) &&
					(iv.getY()+adjustment>=h.getY()-allowance && iv.getY()+adjustment<=h.getY()+allowance)) {
				//place holders turn green if correctly place, red otherwise
				h.setImageResource(R.drawable.greenwireholder);	
				bluePlaced=true;
			} else {
				h.setImageResource(R.drawable.redwireholder);
				bluePlaced=false;
			}
			
		} else if(iv.getId()==R.id.red_wire) {
			
			ImageView h=(ImageView)findViewById(R.id.red_holder);
			if((iv.getX()>=h.getX()-allowance && iv.getX()<=h.getX()+allowance) &&
					(iv.getY()+adjustment>=h.getY()-allowance && iv.getY()+adjustment<=h.getY()+allowance)) {
				h.setImageResource(R.drawable.greenwireholder);	
				redPlaced=true;
			} else {
				h.setImageResource(R.drawable.redwireholder);
				redPlaced=false;
			}
			
		} else if(iv.getId()==R.id.yellow_wire) {
			
			ImageView h=(ImageView)findViewById(R.id.yellow_holder);
			if((iv.getX()>=h.getX()-allowance && iv.getX()<=h.getX()+allowance) &&
					(iv.getY()+adjustment>=h.getY()-allowance && iv.getY()+adjustment<=h.getY()+allowance)) {
				h.setImageResource(R.drawable.greenwireholder);	
				yellowPlaced=true;
			} else {
				h.setImageResource(R.drawable.redwireholder);
				yellowPlaced=false;
			}
			
		} else if(iv.getId()==R.id.green_wire) {
			
			ImageView h=(ImageView)findViewById(R.id.green_holder);
			if((iv.getX()>=h.getX()-allowance && iv.getX()<=h.getX()+allowance) &&
					(iv.getY()+adjustment>=h.getY()-allowance && iv.getY()+adjustment<=h.getY()+allowance)) {
				h.setImageResource(R.drawable.greenwireholder);	
				greenPlaced=true;
			} else {
				h.setImageResource(R.drawable.redwireholder);
				greenPlaced=false;
			}
			
		}
		//check is all are placed
		isTapped(iv);
	}
	
	public void closePrompt(View view) {
		ImageView promptPane = (ImageView)findViewById(R.id.prompt_pane);
		promptPane.setVisibility(View.INVISIBLE);
		
		TextView pi = (TextView)findViewById(R.id.phone_instr);
		pi.setVisibility(View.INVISIBLE);
		
		Button okay = (Button)findViewById(R.id.okay);
		okay.setVisibility(View.INVISIBLE);
	}
	
	//check if all wires are placed correctly, update view
	public void isTapped(View view) {
		if(bluePlaced && redPlaced && yellowPlaced && greenPlaced) {
			ImageView b = (ImageView)findViewById(R.id.blue_holder);
			ImageView r = (ImageView)findViewById(R.id.red_holder);
			ImageView y = (ImageView)findViewById(R.id.yellow_holder);
			ImageView g = (ImageView)findViewById(R.id.green_holder);
			ImageView dbl = (ImageView)findViewById(R.id.double_left);
			ImageView blr = (ImageView)findViewById(R.id.black_right);
			
			b.setVisibility(View.INVISIBLE);
			r.setVisibility(View.INVISIBLE);
			y.setVisibility(View.INVISIBLE);
			g.setVisibility(View.INVISIBLE);
			dbl.setVisibility(View.INVISIBLE);
			blr.setVisibility(View.INVISIBLE);
			
			ImageView pp = (ImageView)findViewById(R.id.prompt_pane);
			TextView pi = (TextView)findViewById(R.id.phone_instr);
			pi.setText(line1);
			Button but = (Button)findViewById(R.id.listen);
			pp.setVisibility(View.VISIBLE);
			pi.setVisibility(View.VISIBLE);
			but.setVisibility(View.VISIBLE);
		}
	}
	
	//manage dialogue
	public void changeDialogue(View view) {
		TextView dialogue = (TextView) findViewById(R.id.phone_instr);
		
		if(count==0) {
			dialogue.setText(line2);
			count++;
		} else if(count==1) {
			dialogue.setText(line3);
			count++;
		} else if(count==2) {
			dialogue.setText(line4);
			count++;
		} else if(count==3) {
			dialogue.setText(line5);
			count++;
		} else if(count==4) {
			dialogue.setText(line6);
			count++;
		} else if(count==5) {
			dialogue.setText(line7);
			count++;
		} else if(count==6) {
			dialogue.setText(line8);
			count++;
		} else if(count==7) {
			dialogue.setText(line9);
			Button b=(Button)findViewById(R.id.listen);
			b.setText("I should investigate nearby apartments to learn of this mystery person");
			count++;
		} else if(count==8){
			this.finish();
			Intent intent = new Intent(this, OpenMap.class);
			//update game info
			data=data+"phonetap";
			intent.putExtra("data", data);
			startActivity(intent);
		}
	}


}
