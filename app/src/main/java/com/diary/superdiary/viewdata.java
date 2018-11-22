package com.diary.superdiary;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class viewdata extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdata);
		String date = getIntent().getStringExtra("extra");
		String note = getIntent().getStringExtra("extra1");
		
		/*Toast.makeText(getApplicationContext(),
				note, Toast.LENGTH_LONG)
                .show();*/
		TextView recdate = (TextView)findViewById(R.id.date);
		TextView recnote = (TextView)findViewById(R.id.note);
		
		recdate.setText(date);
		recnote.setText(note);
		recnote.setMovementMethod(new ScrollingMovementMethod());
	}
	
}
