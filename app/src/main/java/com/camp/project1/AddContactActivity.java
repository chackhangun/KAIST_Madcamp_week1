package com.camp.project1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText name;
    public EditText number;
    public Button btn_save;
    public Button btn_cancel;
    public String addedname;
    public String addednumber;
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
                addedname = name.getText().toString();
                addednumber = number.getText().toString();
                Data data = new Data(addedname, addednumber);
                Intent intent = new Intent(this, PhonebookFragment.class);
                intent.putExtra("newData", (Parcelable) data);
                break;
            case R.id.cancel:
                startActivity(new Intent(this, PhonebookFragment.class));
                break;
        }
    }

    public void getdata(String n, String m){
        addedname = n;
        addednumber = m;
    }
}
