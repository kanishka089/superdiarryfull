package com.diary.superdiary;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class home extends Activity{


	ListView listView;
	//public static ArrayList<String> ArrayofName = new ArrayList<String>();
	ArrayList<getsetinfo> contactList = new ArrayList<getsetinfo>();
	//String date;
	//String note;
	private AdView mAdView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);


		setContentView(R.layout.home);

		/*BannerView mBanner = new BannerView (this);
		RelativeLayout myRelativeLayout = (RelativeLayout) findViewById(R.id.hme);
		myRelativeLayout.addView(mBanner, new LayoutParams(LayoutParams.MATCH_PARENT, 100));
		mBanner.setX(20);
		mBanner.setY(50);
		mBanner.getAdSettings().setPublisherId(923875090);
		mBanner.getAdSettings().setAdspaceId(65822266);
		mBanner.asyncLoadNewBanner();*/

		RelativeLayout rl=(RelativeLayout)findViewById(R.id.hme);
		MobileAds.initialize(this, "ca-app-pub-6699123015236427/1700269194");
		mAdView = (AdView) findViewById(R.id.adView);
		com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		/*AdSize adSize = new AdSize(290,50);
		AdView ad=new AdView(home.this, AdSize.createAdSize(adSize, getBaseContext()),"ca-app-pub-6699123015236427/8031322794");
		//ad.setY(200);
		rl.addView(ad);
		ad.loadAd(new AdRequest());*/


		Button addnew = (Button) findViewById(R.id.addBtn);

		final DatabaseHandler db = new DatabaseHandler(this);


		contactList=db.getAllContacts();
        //Toast.makeText(home.this, "contactList length "+contactList.size(), Toast.LENGTH_SHORT).show();
        if(constants.type==constants.type.FREE){
            //Toast.makeText(home.this, "type.FREE", Toast.LENGTH_SHORT).show();
            if(contactList.size()>=5){
                startActivity(new Intent("com.diary.superdiary.getproversion"));
            }
        }else if(constants.type==constants.type.PAID){
            //Toast.makeText(home.this, "type.PAID", Toast.LENGTH_SHORT).show();
        }

        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new MyCustomBaseAdapter(this, contactList));

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ArrayofName);

        listView.setAdapter(adapter);*/

        //ArrayofName.clear();

        /*gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                int position, long id) {
               Toast.makeText(getApplicationContext(),
                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });*/

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, final View arg1,
					final int position,long arg3) {
				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				// Vibrate for 500 milliseconds
				v.vibrate(50);
				// TODO Auto-generated method stub
				AlertDialog.Builder b=new AlertDialog.Builder(home.this);
				b.setIcon(android.R.drawable.ic_dialog_alert);
				b.setMessage("Choose Delete or Edit");
				b.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int whichButton) {

						 /* Toast.makeText(getApplicationContext(),
                                  "Deleted", Toast.LENGTH_LONG)
                                  .show();*/
						  TextView dbid = (TextView) arg1.findViewById(R.id.cityState);
						  db.deleteRow(Integer.parseInt(dbid.getText().toString()));
						  //ma.notifyDataSetChanged();
						  Intent intent = getIntent();
						    finish();
						    startActivity(intent);
					  }
					});
				b.setNegativeButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                    int whichButton) {
                                //dialog.cancel();
                                TextView dbidsingle = (TextView) arg1.findViewById(R.id.cityState);
                				getsetinfo get= db.getContact(Integer.parseInt(dbidsingle.getText().toString()));
                				String date=get.getDate();
                				String note=get.getnote();
                				//int i=get.getID();
                				/*Toast.makeText(getApplicationContext(),
                						i+"", Toast.LENGTH_LONG)
                                        .show();*/
                				Intent intent = getIntent();
                				finish();
                				startActivity(intent);
                				Intent intent1 = new Intent(home.this, editdata.class);
                                intent1.putExtra("extra", date);
                                intent1.putExtra("extra1", note);
                                intent1.putExtra("extra2", dbidsingle.getText().toString());
                                startActivity(intent1);

                            }
                        });

                b.show();
				return false;
			}

		});



		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {

				// TODO Auto-generated method stub
				//String word = listView.getItemAtPosition(position).toString();
				TextView dbidsingle = (TextView) arg1.findViewById(R.id.cityState);
				getsetinfo get= db.getContact(Integer.parseInt(dbidsingle.getText().toString()));
				String date=get.getDate();
				String note=get.getnote();
				//int i=get.getID();
				/*Toast.makeText(getApplicationContext(),
						i+"", Toast.LENGTH_LONG)
                        .show();*/
				Intent intent = new Intent(home.this, viewdata.class);
                intent.putExtra("extra", date);
                intent.putExtra("extra1", note);
                startActivity(intent);
				//startActivity(new Intent("com.diary.superdiary.viewdata"));
			}

		});


	    addnew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.diary.superdiary.addnew"));
			}
		});



	}
	//static final String[] food=new String[]{"k","s","d","a"};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.onCreate(null);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);  // Add this method.

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);  // Add this method.

	}




}
