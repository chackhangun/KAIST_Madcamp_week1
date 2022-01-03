package com.camp.project1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class MbtiQ6 extends Fragment implements View.OnClickListener{
    public TextView answer1;
    public TextView answer2;
    public Button backbutton;
    public String pastselection;
    public Boolean check;
    MainActivity activity;
    @Override
    public void onAttach(Context context){ //fragment를 activity에 attach할때 호
        super.onAttach(context);
        activity = (MainActivity)getActivity();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //초기화 리소스들
        super.onCreate(savedInstanceState);
        check = false;
    }
    // view 객체 얻어서 초기화/ layout inflate하는곳출

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mbti_q6, container, false);
        answer1 = rootView.findViewById(R.id.Q6A1);
        answer2 = rootView.findViewById(R.id.Q6A2);
        backbutton = rootView.findViewById(R.id.button6);
        backbutton.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        if(check == true){
            activity.mymbti.incrementEI(pastselection,"Undo");
            System.out.println("Undo\n");
        }
        activity.mymbti.print();
        return rootView;

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Q6A1:
                activity.mymbti.incrementTF("T", "Do");
                pastselection = "T";
                break;
            case R.id.Q6A2:
                activity.mymbti.incrementTF("F", "Do");
                pastselection = "F";
                break;
            case R.id.button6:
                activity.replaceFragment(activity.mbtiQ5);
                return;
        }
        check = true;
        activity.replaceFragment(activity.mbtiQ7);
    }
}
