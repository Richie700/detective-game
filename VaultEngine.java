package com.example.detectivegame;

import android.app.Activity;
import android.widget.TextView;
public class VaultEngine {
	
	private int combNum1, combNum2, combNum3, combNum4;
	private int revs1, revs2, revs3, revs4;
	private int revs1count, revs2count, revs3count, revs4count = 0;
	private int direc1, direc2, direc3, direc4 = 0;
	private boolean aligned1, aligned2, aligned3, aligned4 = false;
	private float increment = 3.6f;
	private int left=-1;
	private int right=1;
	
	public VaultEngine() {

		combNum1 = (int)(Math.random()*19+1)*5;
		combNum2 = (int)(Math.random()*19+1)*5;
		combNum3 = (int)(Math.random()*19+1)*5;
		combNum4 = (int)(Math.random()*19+1)*5;
		
		revs1 = (int)(Math.random()*4+1);
		revs2 = (int)(Math.random()*4+1);
		revs3 = (int)(Math.random()*4+1);
		revs4 = (int)(Math.random()*4+1);
	}	
	
	public void checkCombination(float angle) {
		if(!aligned1) {
			if(angle<0) {
				if(Math.abs(angle)==combNum1*increment) {
					if(revs1count==0) {
						setDirections(angle);
					}
					revs1count++;
				}
			} else if(angle>0){
				if(Math.abs(angle)==360-(combNum1*increment)) {
					if(revs1count==0) {
						setDirections(angle);
					}
					revs1count++;
				}
			}		
			if(revs1count==revs1) {
				aligned1=true;
			}
			
			
		} else if(!aligned2) {
			if(angle<0 && direc2<0) {
				if(Math.abs(angle)==combNum2*increment) {
					revs2count++;
				}
			} else if(angle>0 && direc2>0){
				if(Math.abs(angle)==360-(combNum2*increment)) {
					revs2count++;
				}
			}		
			if(revs2count==revs2) {
				aligned2=true;
			}	
			
		} else if(!aligned3) {
			if(angle<0 && direc3<0) {
				if(Math.abs(angle)==combNum3*increment) {
					revs3count++;
				}
			} else if(angle>0 && direc3>0){
				if(Math.abs(angle)==360-(combNum3*increment)) {
					revs3count++;
				}
			}		
			if(revs3count==revs3) {
				aligned3=true;
			}	
			
		} else if(!aligned4) {
			if(angle<0 && direc4<0) {
				if(Math.abs(angle)==combNum4*increment) {
					revs4count++;
				}
			} else if(angle>0 && direc4>0){
				if(Math.abs(angle)==360-(combNum4*increment)) {
					revs4count++;
				}
			}		
			if(revs4count==revs4) {
				aligned4=true;
			}	
		}
	}

	
	private void setDirections(float angle) {
		if(angle<0) {
			direc1=left;
		} else if(angle>0) {
			direc1=right;
		}
		direc2=-direc1;
		direc3=-direc2;
		direc4=-direc3;

	}
	
	
	public int getNumber1() {
		return combNum1;
	}
	public int getNumber2() {
		return combNum2;
	}
	public int getNumber3() {
		return combNum3;
	}	
	public int getNumber4() {
		return combNum4;
	}
	
	public boolean getAligned1() {
		return aligned1;
	}
	public boolean getAligned2() {
		return aligned2;
	}
	public boolean getAligned3() {
		return aligned3;
	}
	public boolean getAligned4() {
		return aligned4;
	}
	
	
	public int getRevs1() {
		return revs1;
	}
	public int getRevs2() {
		return revs2;
	}
	public int getRevs3() {
		return revs3;
	}
	public int getRevs4() {
		return revs4;
	}
	

}
