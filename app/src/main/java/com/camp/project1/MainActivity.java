package com.camp.project1;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public int data_number = 0;
    private PhonebookFragment phonebookFragment;
    private GalleryFragment galleryFragment;
    private CalendarFragment calendarFragment;
    private static final int PERMISSION_NUM = 100;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public ArrayList<Data> datalist;
    public Data data;
    public String name;
    public String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomView = findViewById(R.id.my_navigation);
        bottomView.setOnNavigationItemSelectedListener(listener);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_NUM);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSION_NUM);
        }


        phonebookFragment = new PhonebookFragment();
        galleryFragment = new GalleryFragment();
        calendarFragment = new CalendarFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, phonebookFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_phonebook:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, phonebookFragment).commit();
                    break;
                case R.id.item_gallery:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, galleryFragment).commit();
                    break;
                case R.id.item_calendar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, calendarFragment).commit();
                    break;
            }
            return true;
        }
    };

    boolean checkExternalStorage() {
        String state = Environment.getExternalStorageState();
        // 외부메모리 상태
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // 읽기 쓰기 모두 가능
            Log.d("STATE", "외부메모리 읽기 쓰기 모두 가능");
            Toast.makeText(getApplicationContext(),"외부메모리 읽기 쓰기 모두 가능",Toast.LENGTH_SHORT).show();
            return true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            //읽기전용
            Log.d("STATE", "외부메모리 읽기만 가능");
            Toast.makeText(getApplicationContext(),"외부메모리 읽기만 가능",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // 읽기쓰기 모두 안됨
            Log.d("STATE", "외부메모리 읽기쓰기 모두 안됨 : "+ state);
            Toast.makeText(getApplicationContext(),"외부메모리 읽기쓰기 모두 안됨 : "+ state,Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}