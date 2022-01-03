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
import androidx.appcompat.app.ActionBar;
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
    public com.camp.project1.MbtiQ1 mbtiQ1;
    public com.camp.project1.MbtiQ2 mbtiQ2;
    public com.camp.project1.MbtiQ3 mbtiQ3;
    public com.camp.project1.MbtiQ4 mbtiQ4;
    public com.camp.project1.MbtiQ5 mbtiQ5;
    public com.camp.project1.MbtiQ6 mbtiQ6;
    public com.camp.project1.MbtiQ7 mbtiQ7;
    public com.camp.project1.MbtiQ8 mbtiQ8;
    public com.camp.project1.MbtiQ9 mbtiQ9;
    public com.camp.project1.MbtiQ10 mbtiQ10;
    public com.camp.project1.MbtiQ11 mbtiQ11;
    public com.camp.project1.MbtiQ12 mbtiQ12;
    public com.camp.project1.MbtiResult mbtiResult;
    public MBTI mymbti;
    private static final int PERMISSION_NUM = 100;
    private static boolean permission_check = false;
    public static final String PROVIDER_URI = "content://com.camp.project1.ContactProvider";
    private PermissionSupport permission;

    public String name;
    public String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_main);
        BottomNavigationView bottomView = findViewById(R.id.my_navigation);
        bottomView.setOnNavigationItemSelectedListener(listener);


        permissionCheck();

        phonebookFragment = new PhonebookFragment();
        galleryFragment = new GalleryFragment();
        mbtiQ1 = new MbtiQ1();
        mbtiQ2 = new MbtiQ2();
        mbtiQ3 = new MbtiQ3();
        mbtiQ4 = new MbtiQ4();
        mbtiQ5 = new MbtiQ5();
        mbtiQ6 = new MbtiQ6();
        mbtiQ7 = new MbtiQ7();
        mbtiQ8 = new MbtiQ8();
        mbtiQ9 = new MbtiQ9();
        mbtiQ10 = new MbtiQ10();
        mbtiQ11 = new MbtiQ11();
        mbtiQ12 = new MbtiQ12();
        mbtiResult = new MbtiResult();
        mymbti = new MBTI();
/*
        if(permission_check == false){
            getpermission();
            permission_check = true;
        }*/
        /*
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){

        }*/

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, phonebookFragment).commit();
    }

    private void permissionCheck(){
        permission = new PermissionSupport(this, this);
        if(!permission.checkPermission()){
            permission.requestPermission();
        }
    }
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(!permission.permissionResult(requestCode, permissions, grantResults)){
            Toast.makeText(this, "기능 사용을 위한 권한 동의가 필요합니다.", Toast.LENGTH_SHORT).show();
            permission.requestPermission();
        }
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ1).commit();
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