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

public class MbtiQ3 extends Fragment implements View.OnClickListener{
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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mbti_q3, container, false);
        answer1 = rootView.findViewById(R.id.Q3A1);
        answer2 = rootView.findViewById(R.id.Q3A2);
        backbutton = rootView.findViewById(R.id.button3);
        backbutton.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        if(activity.mymbti.backward == true){
            activity.mymbti.managing_data(pastselection,"Undo");
            System.out.println("Undo\n");
            activity.mymbti.backward = false;
        }
        activity.mymbti.print();
        return rootView;

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Q3A1:
                activity.mymbti.managing_data("T", "Do");
                pastselection = "T";
                break;
            case R.id.Q3A2:
                activity.mymbti.managing_data("F", "Do");
                pastselection = "F";
                break;
            case R.id.button3:
                activity.mymbti.backward = true;
                activity.replaceFragment(activity.mbtiQ2);
                return;
        }
        activity.replaceFragment(activity.mbtiQ4);
    }
}
