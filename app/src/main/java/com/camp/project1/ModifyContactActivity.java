package com.camp.project1;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyContactActivity extends AppCompatActivity implements View.OnClickListener{
    public String original_name;
    public String original_phone;

    public EditText name2;
    public EditText number2;
    public Button btn_save2;
    public Button btn_cancel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_contact);
        name2 = findViewById(R.id.add_name2);
        number2 = findViewById(R.id.add_number2);

        btn_save2 = findViewById(R.id.save2);
        btn_cancel2 = findViewById(R.id.cancel2);
        btn_save2.setOnClickListener(this);
        btn_cancel2.setOnClickListener(this);

        Intent intent = getIntent();
        original_name = intent.getStringExtra("name");
        original_phone = intent.getStringExtra("number");
        System.out.println(original_name);
        name2.setText(original_name);
        number2.setText(original_phone);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.save2:
                /*
                ContentValues contentValues = new ContentValues();
                contentValues.put(ContactsContract.RawContacts.CONTACT_ID, 0);
                contentValues.put(ContactsContract.RawContacts.AGGREGATION_MODE, ContactsContract.RawContacts.AGGREGATION_MODE_DEFAULT);
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
                String[] columns = new String[]{"_id", "name", "phone"};
                Cursor c = getContentResolver().query(Uri.parse(MainActivity.PROVIDER_URI),columns, null,null,null,null);
                if(c != null){
                    while(c.moveToNext()){
                        String id = String.valueOf(c.getInt(0));
                        String name = c.getString(1);
                        String phone = c.getString(2);
                        System.out.println("this name : "+name);
                        System.out.println("original name : "+original_name);
                        if(name.equals(original_name)){
                            System.out.println("if enter");
                            ContentValues updatePhone = new ContentValues();
                            updatePhone.put("phone", phone);

                            getContentResolver().update(Uri.parse(MainActivity.PROVIDER_URI), updatePhone,"_id = " + id, null);
                            System.out.println("nunmber update complete\n");
                            break;
                        }
                    }
                }
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.cancel2:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

}
