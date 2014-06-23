package com.example.zorg;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ZorgTerminal extends Activity {
	
	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zorg);
		
		txt = (TextView)findViewById(R.id.textView1);	
		txt.setMovementMethod(new ScrollingMovementMethod());


		new connectdtb(this,"geterrors").execute(null,null);
	}
}