package com.camp.project1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.camp.project1.ui.calendar.CalendarFragment;
import com.camp.project1.ui.gallery.GalleryFragment;
import com.camp.project1.ui.phonebook.PhonebookFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Data> temp_data = new ArrayList<>();
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosiiton = -1;


    @NotNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapter.ViewHolder holder, int position) {
        //item을 하나하나 보여주는(bind 되는) 함수???
        holder.onBind(temp_data.get(position), position);
    }

    @Override
    public int getItemCount() {//recyclerview의 총 개수
        return temp_data.size();
    }

    public void addlist(ArrayList<Data> dataset){
        temp_data = null;
        temp_data = dataset;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView name;
        TextView number;
        Button button1;
        Button button2;
        Button button3;

        private Data data;
        private int position;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
            button1= (Button) itemView.findViewById(R.id.call);
            button2= (Button) itemView.findViewById(R.id.massage);
            button3= (Button) itemView.findViewById(R.id.setting);


        }

        void onBind(Data data, int position) {
            //photo.setImageResource(data.getimage());

            this.data = data;
            this.position=position;

            name.setText(data.getName());
            number.setText(data.getNumber());

            changeVisibility(selectedItems.get(position));

            itemView.setOnClickListener(this::onClick);
            name.setOnClickListener(this::onClick);
            number.setOnClickListener(this::onClick);


        }

        public void onClick(View v){
            switch (v.getId()){
                case R.id.linearItem:
                    if(selectedItems.get(position)){
                        selectedItems.delete(position);
                    }else {
                        selectedItems.delete(prePosiiton);
                        selectedItems.put(position, true);
                    }
                    if(prePosiiton != -1)notifyItemChanged(prePosiiton);
                    notifyItemChanged(position);
                    prePosiiton = position;
                    break;
            }
        }


        private void changeVisibility(final boolean isExpanded){
            int dpValue = 80;
            float d=context.getResources().getDisplayMetrics().density;
            int height = (int)(dpValue*d);

            ValueAnimator va = isExpanded? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            va.setDuration(300);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                @Override
                public void onAnimationUpdate(ValueAnimator animation){
                    int value =(int) animation.getAnimatedValue();

                    button1.getLayoutParams().height = value;
                    button1.requestLayout();
                    button1.setVisibility(isExpanded? View.VISIBLE : View.GONE);

                    button2.getLayoutParams().height = value;
                    button2.requestLayout();
                    button2.setVisibility(isExpanded? View.VISIBLE : View.GONE);

                    button3.getLayoutParams().height = value;
                    button3.requestLayout();
                    button3.setVisibility(isExpanded? View.VISIBLE : View.GONE);

            }
        });
        va.start();
    }

    }
}
