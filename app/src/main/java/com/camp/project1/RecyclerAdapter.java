package com.camp.project1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Data> temp_data = new ArrayList<>();
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int preposition = -1;
    private Context context;
    @NotNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false); // 만든 xml을 inflate시키는 동작.
        //새로운 view를 만들어서 view holder에 넣어주기 위함이다. why? view(view)는 xml로 이루어져있고 viewholder가 관리한다. adapter가 필요할 때마다 사용한다.
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapter.ViewHolder holder, int position) {
        //item을 하나하나 보여주는 함수???
        holder.onBind(temp_data.get(position), position);

    }

    @Override
    public int getItemCount() {//recyclerview의 총 개수
        return temp_data.size();
    }

    public void additem(Data data){ temp_data.add(data);
    }

    public void addList(ArrayList<Data> dataList){
        temp_data = null;
        temp_data = dataList;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView photo;
        TextView name;
        TextView number;
        ImageButton call;
        ImageButton message;
        ImageButton more;
        LinearLayout linearlayout;
        LinearLayout calllayout;

        private Data data;
        private int position;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            call = itemView.findViewById(R.id.callbutton);
            message = itemView.findViewById(R.id.messagebutton);
            more = itemView.findViewById(R.id.morebutton);
            linearlayout = itemView.findViewById(R.id.linearLayout);
            calllayout = itemView.findViewById(R.id.call_layout);

        }

        void onBind(Data data, int position) {
            this.data = data;
            this.position = position;
            //photo.setImageResource(data.getimage());
            name.setText(data.getName());
            number.setText(data.getNumber());

            changeVisibility(selectedItems.get(position));
            linearlayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearLayout:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(preposition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (preposition != -1) {
                        notifyItemChanged(preposition);
                    }
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    preposition = position;
                    break;

            }
        }

        void changeVisibility(final boolean isExpanded) {
            int dpValue = 50;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0); //고치
            va.setDuration(500);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    calllayout.getLayoutParams().height = (int) valueAnimator.getAnimatedValue();
                    calllayout.requestLayout();
                    calllayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            va.start();
            ////pracitce////
        }
    }

}
