package com.camp.project1;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText name;
    public EditText number;
    public Button btn_save;
    public Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        name = findViewById(R.id.add_name);
        number = findViewById(R.id.add_number);
        btn_save = findViewById(R.id.save);
        btn_cancel = findViewById(R.id.cancel);

        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.save:
                String itemname = name.getText().toString();
                String itemnumber = number.getText().toString();

                ContentValues newContact = new ContentValues();
                newContact.put("name", itemname);
                newContact.put("phone", itemnumber);

                getContentResolver().insert(Uri.parse(MainActivity.PROVIDER_URI), newContact);
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.cancel:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

}
