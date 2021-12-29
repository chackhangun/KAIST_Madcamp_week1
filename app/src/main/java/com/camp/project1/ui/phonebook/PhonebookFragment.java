package com.camp.project1.ui.phonebook;

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
import com.camp.project1.MainActivity;
import com.camp.project1.R;
import com.camp.project1.RecyclerAdapter;
public class PhonebookFragment extends Fragment {

    public RecyclerAdapter recyclerAdapter;

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

        Data testdata = new Data("choidaegun", "010-5005-6743");
        recyclerAdapter.additem(testdata);
        recyclerAdapter.notifyDataSetChanged();

        return fragmentphonebook;
    }

}