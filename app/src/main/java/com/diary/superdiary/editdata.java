package com.diary.superdiary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editdata extends Activity {
	
	final DatabaseHandler db = new DatabaseHandler(this);
	EditText note;
	CharSequence s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.editdata);
		
		final String date = getIntent().getStringExtra("extra");
		final String note = getIntent().getStringExtra("extra1");
		final String id = getIntent().getStringExtra("extra2");
		
		/*Toast.makeText(getApplicationContext(),
				note, Toast.LENGTH_LONG)
                .show();*/
		final TextView recdate = (TextView)findViewById(R.id.date);
		final TextView recnote = (TextView)findViewById(R.id.note);
		
		recdate.setText(date);
		recnote.setText(note);

		Button set = (Button) findViewById(R.id.set);
		set.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String notes=recnote.getText().toString();
				String dates=recdate.getText().toString();
	        	if(!notes.equals("")){
					db.updaterow(id, notes, dates);
	
					finish();
		            Toast.makeText(editdata.this, "Record Edited", Toast.LENGTH_SHORT).show();

	        	}
			}
		});
		
		
	
	}
	@Override
   public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	         
	        switch (item.getItemId())
	        {
	        case R.id.menu_save:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	        	String notes=note.getText().toString();
	        	if(!notes.equals("")){
					db.addContact(new getsetinfo(s.toString(), notes));
	
					finish();
		            Toast.makeText(editdata.this, "Saved", Toast.LENGTH_SHORT).show();
	        	
	            return true;
	        	}
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    } 
	
}
