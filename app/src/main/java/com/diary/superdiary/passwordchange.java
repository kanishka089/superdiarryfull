package com.diary.superdiary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class passwordchange extends Activity{
	EditText pass;
	String passtext;
	final DatabaseHandler db2 = new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.passwordchange);
		Button set = (Button) findViewById(R.id.set);
		pass = (EditText) findViewById(R.id.password);
		//db2.addContactlogin(new getsetinfologin("kani"));
		set.setOnClickListener(new View.OnClickListener() {
		
		
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passtext=pass.getText().toString();
	        	if(!passtext.equals("")){
		        	db2.addContactlogin(new getsetinfologin(passtext));
		        	
		        	//Toast.makeText(this, passtext, Toast.LENGTH_SHORT).show();
					finish();
		            Toast.makeText(passwordchange.this, "Password Saved", Toast.LENGTH_SHORT).show();
	        	}
	        	else{
	        		 Toast.makeText(passwordchange.this, "Please Enter a password", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId())
        {
        case R.id.menu_save:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
        	passtext=pass.getText().toString();
        	if(!passtext.equals("")){
	        	db2.addContactlogin(new getsetinfologin(passtext));
	        	
	        	//Toast.makeText(this, passtext, Toast.LENGTH_SHORT).show();
				finish();
	            Toast.makeText(passwordchange.this, "Password Saved", Toast.LENGTH_SHORT).show();
        	}
        	else{
        		 Toast.makeText(passwordchange.this, "Please Enter a password", Toast.LENGTH_SHORT).show();
        	}
            //startActivity(new Intent("android.intent.action.MAIN"));
            
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	/*@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}*/
	

	

}
