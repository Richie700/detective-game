package com.example.detectivegame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class DialView extends ImageView  {

	//angles for movement calculations
	public float angle, old_angle = 0.0f;
	//update on rotations
	private DialListener listener;

	public interface DialListener{
		public void onDialChanged(int arg);
	}
  
	public void setDialListener(DialListener listener) {
		this.listener = listener;
	}
  
	public DialView(Context context) {
		super(context);
		initialize();
	}
  
	public DialView(Context context, AttributeSet attSet) {
		super(context, attSet);
		initialize();
	}
  
	public DialView(Context context, AttributeSet attSet, int defStyle) {
		super(context, attSet, defStyle);
		initialize();
	}
  
	//calculate angle rotated
	private float getTheta(float x, float y) {
		float sx = x - (getWidth() / 2.0f);
		float sy = y - (getHeight() / 2.0f);
    
		float length = (float)Math.sqrt( sx*sx + sy*sy);
		float nx = sx / length;
		float ny = sy / length;
		float theta = (float)Math.atan2( ny, nx );
    
		final float rad2deg = (float)(180.0/Math.PI);
		float thetaDeg = theta*rad2deg;
    
		return (thetaDeg < 0) ? thetaDeg + 360.0f : thetaDeg;
	}
  
	//set listener
	public void initialize() {
		this.setImageResource(R.drawable.dial);
		
		setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				float x = event.getX(0);
				float y = event.getY(0);
				float theta = getTheta(x,y);

				switch(event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_POINTER_DOWN:
					old_angle = theta;
					break;
				case MotionEvent.ACTION_MOVE:
					invalidate();
					float delta_theta = theta - old_angle;
					old_angle = theta;
					int direction = (delta_theta > 0) ? 1 : -1;
					angle += 3*direction;	
					if(angle%360==0) {
						angle=0;
					}
					notifyListener(direction);
					break;
				}
				return true;
			}
		});
	}
  
	
	private void notifyListener(int arg) {
		if (null!=listener)
			listener.onDialChanged(arg);
	}
  
	protected void onDraw(Canvas c) {
		c.rotate(angle,getWidth()/2,getHeight()/2);   
		super.onDraw(c);
	}	
} 
