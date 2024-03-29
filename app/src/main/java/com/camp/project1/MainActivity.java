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

import com.camp.project1.gallery.GalleryFragment;
import com.camp.project1.mbti.MbtiMain;
import com.camp.project1.mbti.MbtiQ1;
import com.camp.project1.mbti.MbtiQ10;
import com.camp.project1.mbti.MbtiQ11;
import com.camp.project1.mbti.MbtiQ12;
import com.camp.project1.mbti.MbtiQ2;
import com.camp.project1.mbti.MbtiQ3;
import com.camp.project1.mbti.MbtiQ4;
import com.camp.project1.mbti.MbtiQ5;
import com.camp.project1.mbti.MbtiQ6;
import com.camp.project1.mbti.MbtiQ7;
import com.camp.project1.mbti.MbtiQ8;
import com.camp.project1.mbti.MbtiQ9;
import com.camp.project1.mbti.MbtiResult;
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
    private GalleryFragment galleryFragment;
    public MbtiMain mbtiMain;
    public MbtiQ1 mbtiQ1;
    public MbtiQ2 mbtiQ2;
    public MbtiQ3 mbtiQ3;
    public MbtiQ4 mbtiQ4;
    public MbtiQ5 mbtiQ5;
    public MbtiQ6 mbtiQ6;
    public MbtiQ7 mbtiQ7;
    public MbtiQ8 mbtiQ8;
    public MbtiQ9 mbtiQ9;
    public MbtiQ10 mbtiQ10;
    public MbtiQ11 mbtiQ11;
    public MbtiQ12 mbtiQ12;
    public MbtiResult mbtiResult;
    public MBTI mymbti;
    private static final int PERMISSION_NUM = 100;
    private static boolean permission_check = false;
    public static final String PROVIDER_URI = "content://com.camp.project1.ContactProvider";
    private PermissionSupport permission;

    public String name;
    public Boolean tap3_check = false;
    public Boolean second_check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //permissionCheck();
        getpermission();
        ActionBar ab = getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_main);
        BottomNavigationView bottomView = findViewById(R.id.my_navigation);
        bottomView.setOnNavigationItemSelectedListener(listener);

        phonebookFragment = new PhonebookFragment();
        galleryFragment = new GalleryFragment();
        mbtiMain = new MbtiMain();
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


        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, phonebookFragment).commit();
    }

    private void permissionCheck(){
        permission = new PermissionSupport(this, this);
        permission.requestPermission();
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
                    second_check = true;
                    break;
                case R.id.item_gallery:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, galleryFragment).commit();
                    second_check = true;
                    break;
                case R.id.item_calendar:
                    System.out.println(mymbti.mbti_page);
                    switch (mymbti.mbti_page) {
                        case 0:
                            mymbti = new MBTI();
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiMain).commit();
                            break;
                        case 1:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ1).commit();
                            break;
                        case 2:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ2).commit();
                            break;
                        case 3:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ3).commit();
                            break;
                        case 4:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ4).commit();
                            break;
                        case 5:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ5).commit();
                            break;
                        case 6:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ6).commit();
                            break;
                        case 7:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ7).commit();
                            break;
                        case 8:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ8).commit();
                            break;
                        case 9:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ9).commit();
                            break;
                        case 10:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ10).commit();
                            break;
                        case 11:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ11).commit();
                            break;
                        case 12:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiQ12).commit();
                            break;
                        case 13:
                            if(tap3_check == false | second_check == true){
                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiResult).commit();
                                second_check = false;
                            }
                            else{
                                mymbti = new MBTI();
                                tap3_check = false;
                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mbtiMain).commit();
                            }
                            break;
                    }


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