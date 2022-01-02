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
                /*
                ContentValues contentValues = new ContentValues();
                contentValues.put(ContactsContract.RawContacts.CONTACT_ID, 0);
                contentValues.put(ContactsContract.RawContacts.AGGREGATION_MODE, ContactsContract.RawContacts.AGGREGATION_MODE_DISABLED);
                Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
                long rawContactId = ContentUris.parseId(rawContactUri);

                contentValues.clear();
                contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                contentValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,itemname);
                contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, itemnumber);
                Uri dataUri = getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contentValues);
                startActivity(new Intent(this, MainActivity.class));
                 */
                /*
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE) ;
                intent.putExtra(ContactsContract.Intents.Insert.NAME, itemname);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, itemnumber);
                startActivity(new Intent(this, MainActivity.class));
                */
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
