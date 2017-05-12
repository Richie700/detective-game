package com.example.detectivegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Newspaper extends Activity {

	//the hidden message, predefined for placement in text
	private String hiddenMessage = "postBHBCdiner";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newspaper);
		
		//place the hidden message into a given block of text if possible
		String []hiddenMessageArr = hiddenMessage.split("");
		String [] textArr = "The Charter of the United Nations stands ready for use. Everything now depends, as President Truman said in his closing address to the conference, on whether nations have \"the will to use it\". When it has been ratified by the five Great Powers and by a majority of the other signatory States we shall have once again a rule of law in the world, and for the first time this rule will have the authority of all the greatest nations. The finest achievement is to be found not in any of the 111 articles but in the signatures of the United States and the Union of Soviet Republics upon the same Charter.".split("");
		
		//scratch out the corresponding letters
		int scratched =1;
		for(int i=1;i<hiddenMessageArr.length;i++) {
			for(int j=scratched;j<textArr.length;j++) {
				if(hiddenMessageArr[i].equalsIgnoreCase(textArr[j])) {
					textArr[j]="*";
					scratched = j+1;
					j=textArr.length;
				}
			}
		}
		//string paragraph together after separating
		String paragraph = "";
		for(int i=0;i<textArr.length;i++){
			paragraph = paragraph+textArr[i];
		}
		
		TextView test = (TextView) findViewById(R.id.article_content);
		test.setText(paragraph);
	}
	
	//check that the player's guess matches the hidden word, alert them if or if not
	public void checkGuess(View view) {
		TextView alert = (TextView) findViewById(R.id.validity_alert);
		
		EditText guess = (EditText) findViewById(R.id.message_guess);
		String check = guess.getText().toString();
		check=check.replaceAll(" ", "");
		if(check.equalsIgnoreCase(hiddenMessage)){
			alert.setVisibility(View.INVISIBLE);
			guess.setVisibility(View.INVISIBLE);
			Button decipher=(Button) findViewById(R.id.decipher_button);
			decipher.setVisibility(View.INVISIBLE);
			
			Button okay=(Button) findViewById(R.id.okay_exit);
			okay.setVisibility(View.VISIBLE);
			
			TextView message = (TextView) findViewById(R.id.message);
			message.setVisibility(View.VISIBLE);
			
			ImageView paper=(ImageView) findViewById(R.id.paper);
			paper.setVisibility(View.VISIBLE);
		}else {
			alert.setText("Nothing meaningful can be deciphered, try a different combination");
			alert.setTextColor(Color.RED);
		}
		
	}
	
	//close instructions
	public void closePrompt(View view) {
		ImageView promptPane = (ImageView)findViewById(R.id.prompt_pane);
		promptPane.setVisibility(View.INVISIBLE);
		
		TextView promptText= (TextView)findViewById(R.id.newspaper_instr);
		promptText.setVisibility(View.INVISIBLE);
		
		Button promptButton=(Button)findViewById(R.id.newspaper_ok);
		promptButton.setVisibility(View.INVISIBLE);
		
		EditText guess= (EditText)findViewById(R.id.message_guess);
		guess.setVisibility(View.VISIBLE);
		
		Button decipher=(Button)findViewById(R.id.decipher_button);
		decipher.setVisibility(View.VISIBLE);

	}
	
	//open game map
	public void openMap(View view) {
		this.finish();
		Intent intent = new Intent(this, OpenMap.class);
		intent.putExtra("data", "newspaper");
		startActivity(intent);
	}
}
