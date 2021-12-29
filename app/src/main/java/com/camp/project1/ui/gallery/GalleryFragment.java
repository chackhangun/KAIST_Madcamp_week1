package com.camp.project1.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.camp.project1.R;
import com.camp.project1.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentgallery = inflater.inflate(R.layout.fragment_gallery, container, false);


        return fragmentgallery;
    }


}