package com.camp.project1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MbtiQ1 extends Fragment implements View.OnClickListener{
    public TextView answer1;
    public TextView answer2;
    MainActivity activity;
    @Override
    public void onAttach(Context context){ //fragment를 activity에 attach할때 호
        super.onAttach(context);
        activity = (MainActivity)getActivity();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //초기화 리소스들
        super.onCreate(savedInstanceState);

    }
    // view 객체 얻어서 초기화/ layout inflate하는곳출

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mbti_q1, container, false);
        answer1 = rootView.findViewById(R.id.Q1A1);
        answer2 = rootView.findViewById(R.id.Q1A2);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        return rootView;

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Q1A1:
                activity.mymbti.incrementEI("E");
                break;
            case R.id.Q1A2:
                activity.mymbti.incrementEI("I");
                break;
        }
        //activity.getSupportFragmentManager().beginTransaction().replace(R.id.mbtifragment_container1, activity.mbtiQ2).commit();
        activity.replaceFragment(activity.mbtiQ2);
    }
}
