package com.example.zorg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private Button login_button;
	private EditText usernameEditText, passwordEditText;
	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		login_button = (Button) findViewById(R.id.button1);
		login_button.setOnClickListener(this);
		usernameEditText = (EditText) findViewById(R.id.editText1);
		passwordEditText = (EditText) findViewById(R.id.editText2);

		txt = (TextView) findViewById(R.id.textView1);
	}

	
	// @Override
	public void onClick(View v) {
		String givenUsername = usernameEditText.getEditableText().toString();
		String givenPassword = passwordEditText.getEditableText().toString();
		new connectdtb(this,"login").execute(givenUsername, givenPassword);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
