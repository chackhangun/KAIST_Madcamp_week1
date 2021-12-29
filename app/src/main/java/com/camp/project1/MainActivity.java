package com.camp.project1;

import android.os.Bundle;
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


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PhonebookFragment phonebookFragment;
    private GalleryFragment galleryFragment;
    private CalendarFragment calendarFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        BottomNavigationView bottomView = findViewById(R.id.my_navigation);

        bottomView.setOnNavigationItemSelectedListener(listener);
        phonebookFragment = new PhonebookFragment();
        galleryFragment = new GalleryFragment();
        calendarFragment = new CalendarFragment();
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

    //Data testdata2 = new Data("temp","2123123");


}