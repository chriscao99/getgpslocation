package com.chriscaoiscool.getgpslocation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ContactsActivity extends ActionBarActivity {

    Button contact3;
    EditText name1, name2, name3, phone1, phone2, phone3;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        contact3 = (Button)findViewById(R.id.button8);

        name1 = (EditText)findViewById(R.id.editText2);
        name2 = (EditText)findViewById(R.id.editText4);
        name3 = (EditText)findViewById(R.id.editText6);

        phone1 = (EditText)findViewById(R.id.editText3);
        phone2 = (EditText)findViewById(R.id.editText5);
        phone3 = (EditText)findViewById(R.id.editText7);



        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("name1", name1.getText().toString());
                intent.putExtra("phone1", phone1.getText().toString());
                intent.putExtra("name2", name2.getText().toString());
                intent.putExtra("phone2", phone2.getText().toString());
                intent.putExtra("name3", name3.getText().toString());
                intent.putExtra("phone3", phone3.getText().toString());
                startActivity(intent);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
