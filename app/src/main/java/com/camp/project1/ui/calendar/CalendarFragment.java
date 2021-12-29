package com.camp.project1.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.camp.project1.R;


public class CalendarFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentcalendar = inflater.inflate(R.layout.fragment_calendar, container, false);
        return fragmentcalendar;
    }

}