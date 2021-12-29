package com.camp.project1;

<<<<<<< HEAD
import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
=======
import android.os.Bundle;
>>>>>>> 6dc5a766d9e10a812b32cb482206f12fe077b157
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.camp.project1.ui.calendar.CalendarFragment;
import com.camp.project1.ui.gallery.GalleryFragment;
import com.camp.project1.ui.phonebook.PhonebookFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
=======
>>>>>>> 6dc5a766d9e10a812b32cb482206f12fe077b157


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

<<<<<<< HEAD

=======
>>>>>>> 6dc5a766d9e10a812b32cb482206f12fe077b157
public class MainActivity extends AppCompatActivity {
    private PhonebookFragment phonebookFragment;
    private GalleryFragment galleryFragment;
    private CalendarFragment calendarFragment;
<<<<<<< HEAD
    private static final int PERMISSION_NUM = 100;
=======

>>>>>>> 6dc5a766d9e10a812b32cb482206f12fe077b157

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        BottomNavigationView bottomView = findViewById(R.id.my_navigation);

        bottomView.setOnNavigationItemSelectedListener(listener);
<<<<<<< HEAD

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_NUM);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSION_NUM);
        }

        phonebookFragment = new PhonebookFragment();
        galleryFragment = new GalleryFragment();
        calendarFragment = new CalendarFragment();

=======
        phonebookFragment = new PhonebookFragment();
        galleryFragment = new GalleryFragment();
        calendarFragment = new CalendarFragment();
>>>>>>> 6dc5a766d9e10a812b32cb482206f12fe077b157
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
<<<<<<< HEAD
=======

>>>>>>> 6dc5a766d9e10a812b32cb482206f12fe077b157
    //Data testdata2 = new Data("temp","2123123");


}