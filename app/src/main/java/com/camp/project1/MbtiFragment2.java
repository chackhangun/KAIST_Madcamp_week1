package com.camp.project1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class MbtiFragment2 extends Fragment implements View.OnClickListener{
    public Button btn_next;
    public Button btn_prior;
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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mbti2, container, false);
        btn_prior = rootView.findViewById(R.id.priorbutton2);
        btn_prior.setOnClickListener(this);
        btn_next = rootView.findViewById(R.id.nextbutton2);
        btn_next.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.nextbutton2:
                activity.replaceFragment(activity.mbtiFragment3);
                break;
            case R.id.priorbutton2:
                activity.replaceFragment(activity.mbtiFragment);
                break;

        }


    }
}
