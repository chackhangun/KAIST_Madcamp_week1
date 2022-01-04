package com.camp.project1.mbti;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.camp.project1.MainActivity;
import com.camp.project1.R;

import org.w3c.dom.Text;

public class MbtiQ2 extends Fragment implements View.OnClickListener{
    public TextView answer1;
    public TextView answer2;
    public Button backbutton;
    public String pastselection;
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
        //ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mbti, container, false);
        //btn_next = rootView.findViewById(R.id.nextbutton1);
        //btn_next.setOnClickListener(this);
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mbti_q2, container, false);
        answer1 = rootView.findViewById(R.id.Q2A1);
        answer2 = rootView.findViewById(R.id.Q2A2);
        backbutton = rootView.findViewById(R.id.button2);
        backbutton.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);

        if(activity.mymbti.backward == true){
            activity.mymbti.managing_data(pastselection,"Undo");
            System.out.println("Undo\n");
            activity.mymbti.backward = false;
        }
        activity.mymbti.mbti_page = 2;
        activity.mymbti.print();
        return rootView;

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Q2A1:
                activity.mymbti.managing_data("S", "Do");
                pastselection = "S";
                break;
            case R.id.Q2A2:
                activity.mymbti.managing_data("N", "Do");
                pastselection = "N";
                break;
            case R.id.button2:
                activity.mymbti.backward = true;
                activity.replaceFragment(activity.mbtiQ1);
                return;
        }
        activity.replaceFragment(activity.mbtiQ3);
    }
}
