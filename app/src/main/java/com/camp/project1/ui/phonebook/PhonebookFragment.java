package com.camp.project1.ui.phonebook;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camp.project1.Data;
import android.provider.ContactsContract;

import com.camp.project1.R;
import com.camp.project1.RecyclerAdapter;

import java.util.ArrayList;

public class PhonebookFragment extends Fragment {

    public RecyclerAdapter recyclerAdapter;
    public ArrayList<Data> datalist;
    @Nullable
    @org.jetbrains.annotations.Nullable
    public Context ct;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentphonebook = inflater.inflate(R.layout.fragment_phonebook, container, false);

        RecyclerView recyclerView = fragmentphonebook.findViewById(R.id.recyclerview);

        ct = container.getContext();//fragment에서 동작할 수 있게 해주는 것.

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ct);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        datalist = getContacts(ct);
        recyclerAdapter.addList(datalist);

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