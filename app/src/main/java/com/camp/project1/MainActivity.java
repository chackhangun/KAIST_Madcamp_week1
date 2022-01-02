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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private com.camp.project1.PhonebookFragment phonebookFragment;
    private com.camp.project1.GalleryFragment galleryFragment;
    public com.camp.project1.MbtiFragment mbtiFragment;
    public com.camp.project1.MbtiFragment2 mbtiFragment2;
    public com.camp.project1.MbtiFragment3 mbtiFragment3;
    public com.camp.project1.MbtiResult mbtiResult;

    private static final int PERMISSION_NUM = 100;
    private static boolean permission_check = false;
    public static final String PROVIDER_URI = "content://com.camp.project1.ContactProvider";

    public String name;
    public String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomView = findViewById(R.id.my_navigation);
        bottomView.setOnNavigationItemSelectedListener(listener);


        if(permission_check == false){
            getpermission();
            permission_check = true;
        }
        phonebookFragment = new PhonebookFragment();
        galleryFragment = new GalleryFragment();
        mbtiFragment = new MbtiFragment();
        mbtiFragment2 = new MbtiFragment2();
        mbtiFragment3 = new MbtiFragment3();
        mbtiResult = new MbtiResult();
        /*
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){

        }*/

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
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiFragment).commit();
                    break;
            }
            return true;
        }
    };

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    public void getpermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_NUM);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSION_NUM);
    }
}