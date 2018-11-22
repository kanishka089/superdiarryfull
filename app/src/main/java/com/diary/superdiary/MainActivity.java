package com.diary.superdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {
	EditText pass;
	public static String ps="empty";
	TextView url1;
	RelativeLayout rl;
	private AdView mAdView;
	private InterstitialAd mAdViewIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		final DatabaseHandler db1 = new DatabaseHandler(this);
		//db1.addContact(new getsetinfo("21/12/2013","this is test data"));
		//db1.addContactlogin(new getsetinfologin("kani"));
		/*getsetinfo get= db1.getContact(5);
		String date=get.getDate();
		String note=get.getnote();
		Toast.makeText(getApplicationContext(),
				date, Toast.LENGTH_LONG)
                .show();*/
		/*url1=(TextView)findViewById(R.id.url);
		Pattern pattern = Pattern.compile("google.com");
		//jmt: prefix our pattern with http://
		Linkify.addLinks(url1, pattern, "http://");*/
		
		int i=db1.getContactsCountlogin();
		if(i==0){
			startActivity(new Intent("com.diary.superdiary.passchange"));
			//finish();
		}
		else{
			getsetinfologin get= db1.getContactlogin(1);
			ps=get.getpass();
		}
		/*Toast.makeText(getApplicationContext(),
				i+"", Toast.LENGTH_LONG)
                .show();*/
		setContentView(R.layout.activity_main);
		Display display = getWindowManager().getDefaultDisplay();
		
		rl=(RelativeLayout)findViewById(R.id.login);
		MobileAds.initialize(this, "ca-app-pub-6699123015236427/8031322794");
		mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		/*AdSize adSize = new AdSize(290,50);
		//AdView ad= new AdView(this, AdSize.BANNER, "ca-app-pub-6699123015236427/1700269194"); 
		AdView ad=new AdView(MainActivity.this, AdSize.createAdSize(adSize, getBaseContext()),"ca-app-pub-6699123015236427/8031322794");
		//ad.setY(200);
		rl.addView(ad);
		ad.loadAd(new AdRequest());*/

		/*if(ps.equals("empty")){
			startActivity(new Intent("com.diary.superdiary.passchange"));
			
		}*/
		mAdViewIn=new InterstitialAd(this);
		mAdViewIn.setAdUnitId("ca-app-pub-6699123015236427/5822572791");
		AdRequest request=new AdRequest.Builder().build();
		mAdViewIn.loadAd(request);

		Button enter = (Button) findViewById(R.id.button1);
		pass = (EditText) findViewById(R.id.pass);
		final Toast error = Toast.makeText(this, "Invalid Login",
				Toast.LENGTH_SHORT);
		enter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getsetinfologin get= db1.getContactlogin(1);
				ps=get.getpass();
				if (pass.getText().toString().equals(ps)) {
					startActivity(new Intent("com.diary.superdiary.login"));
					
					finish();
				} else {
					pass.setText("");
					error.show();
				}
				if (mAdViewIn.isLoaded()){
					mAdViewIn.show();
				}
			}
		});
	
	}


}
