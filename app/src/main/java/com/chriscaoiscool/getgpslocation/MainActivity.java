package com.chriscaoiscool.getgpslocation;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private CounterClass countertimer;
    Button btnShowLocation, showMap, start, cancel, contacts;
    TextView mTextField;
    EditText count;
    String counter = "";
    String name1, name2, name3, phone1, phone2, phone3;

    final Context context = this;
    GPSTracker gps;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        name1 = extras.getString("name1");
        name2 = extras.getString("name2");
        name3 = extras.getString("name3");

        phone1 = extras.getString("phone1");
        phone2 = extras.getString("phone2");
        phone3 = extras.getString("phone3");


        count = (EditText)findViewById(R.id.editText);


        start = (Button)findViewById(R.id.button3);
        cancel = (Button)findViewById(R.id.button4);
        contacts = (Button)findViewById(R.id.button5);

        mTextField = (TextView)findViewById(R.id.textView);



        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int c = Integer.parseInt(count.getText().toString());
                int cc = (c*60000);
                countertimer = new CounterClass(cc, 1000);
                countertimer.start();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                countertimer.cancel();
            }
        });




        btnShowLocation = (Button) findViewById(R.id.button);
        showMap = (Button) findViewById(R.id.button2);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                gps = new GPSTracker(MainActivity.this);

                if(gps.isCanGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Your Location is -\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }
                else{
                    gps.showSettingsAlert();
                }
            }
        });


        showMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                gps = new GPSTracker(MainActivity.this);


                if(gps.isCanGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    startActivity(intent);
                }
                else{
                    gps.showSettingsAlert();
                }

            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactsActivity.class);
                startActivity(intent);
            }
        });


    }
    public class CounterClass extends CountDownTimer{
        public CounterClass(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextField.setText("Seconds Remaining: " + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            gps = new GPSTracker(MainActivity.this);

            SmsManager sms = SmsManager.getDefault();

            if(gps.isCanGetLocation()){
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                Toast.makeText(getApplicationContext(), "Your Location is -\nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();


                sms.sendTextMessage(phone1 + "", null, "Hi " + name1 + ", your friend Chris may be in trouble while on his run. You can view his last recorded location at: " + "http://maps.google.com/?q=" + latitude + "," + longitude, null, null);
                sms.sendTextMessage(phone2 + "", null, "Hi " + name2 + ", your friend Chris may be in trouble while on his run. You can view his last recorded location at: " + "http://maps.google.com/?q=" + latitude + "," + longitude, null, null);
                sms.sendTextMessage(phone3 + "", null, "Hi " + name3 + ", your friend Chris may be in trouble while on his run. You can view his last recorded location at: " + "http://maps.google.com/?q=" + latitude + "," + longitude, null, null);

                Toast.makeText(getApplicationContext(), "Contacts Alerted", Toast.LENGTH_LONG).show();
            }
            else{
                gps.showSettingsAlert();
            }
        }
    }

}
