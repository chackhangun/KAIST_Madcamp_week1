package com.camp.project1;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class PhonebookFragment extends Fragment{
    public RecyclerAdapter recyclerAdapter;
    public ArrayList<Data> datalist;
    @Nullable
    @org.jetbrains.annotations.Nullable
    public Context ct;
    public Activity act;
    public FloatingActionButton fab;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentphonebook = inflater.inflate(R.layout.fragment_phonebook, container, false);
        act = getActivity();
        ct = getContext();//fragment에서 동작할 수 있게 해주는 것.
        RecyclerView recyclerView = fragmentphonebook.findViewById(R.id.recyclerview);
        fab = fragmentphonebook.findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ct, AddContactActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ct);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.setActivity(act);

        recyclerView.setAdapter(recyclerAdapter);

        datalist = getContacts(ct);//ArrayList<Data>를 반환
        recyclerAdapter.addList(datalist);


        String[] columns = new String[]{"_id", "name", "phone"};
        Cursor c = getActivity().getContentResolver().query(Uri.parse(MainActivity.PROVIDER_URI),columns, null,null,null,null);
        if(c != null){
            while(c.moveToNext()){
                String name = c.getString(1);
                String phone = c.getString(2);
                Data data = new Data(name,phone);
                recyclerAdapter.additem(data);
            }
        }

        return fragmentphonebook;
    }


    public ArrayList<Data> getContacts(Context context){
        ArrayList<Data> dataList = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();

        Uri phoneuri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = resolver.query(phoneuri, projection, null, null, null); //resolver 가 provider에게 정보 요청

        if(cursor != null){
            while(cursor.moveToNext()){
                int nameIndex = cursor.getColumnIndex(projection[1]);
                int numberIndex = cursor.getColumnIndex(projection[2]);

                Data data = new Data(cursor.getString(nameIndex), cursor.getString(numberIndex));
                dataList.add(data);
            }
        }
        cursor.close(); //데이터 계열은 반드시 닫아줘야함
        return dataList;
    }

}