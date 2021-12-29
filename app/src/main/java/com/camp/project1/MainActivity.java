package com.camp.project1;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.camp.project1.ui.calendar.CalendarFragment;
import com.camp.project1.ui.gallery.GalleryFragment;
import com.camp.project1.ui.phonebook.PhonebookFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import org.jetbrains.annotations.NotNull;

import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PhonebookFragment phonebookFragment;
    private GalleryFragment galleryFragment;
    private CalendarFragment calendarFragment;

    private static final int MULTIPLE_PERMISSION = 10235;

    private String[] PERMISSIONS = {
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, MULTIPLE_PERMISSION);
        }else{
            setContentView(R.layout.activity_main);

            FrameLayout frameLayout = findViewById(R.id.frameLayout);
            BottomNavigationView bottomView = findViewById(R.id.my_navigation);

            bottomView.setOnNavigationItemSelectedListener(listener);
            phonebookFragment = new PhonebookFragment();
            galleryFragment = new GalleryFragment();
            calendarFragment = new CalendarFragment();
        }

        };


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    //권한 요청에 대한 결과 처리
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MULTIPLE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setContentView(R.layout.activity_main);

                    FrameLayout frameLayout = findViewById(R.id.frameLayout);
                    BottomNavigationView bottomView = findViewById(R.id.my_navigation);

                    bottomView.setOnNavigationItemSelectedListener(listener);
                    phonebookFragment = new PhonebookFragment();
                    galleryFragment = new GalleryFragment();
                    calendarFragment = new CalendarFragment();
                } else {
                    // 하나라도 거부한다면.
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("앱 권한");
                    alertDialog.setMessage("해당 앱의 원할한 기능을 이용하시려면 애플리케이션 정보>권한> 에서 모든 권한을 허용해 주십시오");
                    // 권한설정 클릭시 이벤트 발생
                    alertDialog.setPositiveButton("권한설정",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                                    startActivity(intent);
                                    dialog.cancel();
                                }
                            });
                    //취소
                    alertDialog.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    alertDialog.show();
                }
                return;
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, calendarFragment).commit();
                    break;
            }
            return true;
        }
    };

}