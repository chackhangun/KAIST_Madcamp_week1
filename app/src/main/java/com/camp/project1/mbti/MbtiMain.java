package com.camp.project1.mbti;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.camp.project1.MainActivity;
import com.camp.project1.R;

public class MbtiMain extends Fragment implements View.OnClickListener{
    public TextView answer1;
    MainActivity activity;
    public String pastselection;

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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mbti_main, container, false);
        answer1 = rootView.findViewById(R.id.start);

        answer1.setOnClickListener(this);

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

        activity.replaceFragment(activity.mbtiQ1);
    }
}
