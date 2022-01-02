package com.camp.project1;
import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Data> temp_data = new ArrayList<>();
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private SparseBooleanArray selectedItems2 = new SparseBooleanArray();
    private int preposition = -1;
    private int preposition2 = -1;
    private Context context;
    Activity activity;
    public boolean check = false;

    @NotNull
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false); // 만든 xml을 inflate시키는 동작.
            //새로운 view를 만들어서 view holder에 넣어주기 위함이다. why? view(view)는 xml로 이루어져있고 viewholder가 관리한다. adapter가 필요할 때마다 사용한다.
            context = parent.getContext(); //////// check
            ViewHolder viewholder =  new ViewHolder(view);
            return viewholder;
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

        public void additem(Data data) {
            temp_data.add(data);
        }

        public void addList(ArrayList<Data> dataList) {
            temp_data = dataList;
        }

        public void setActivity(Activity act){
            activity = act;
        }
        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView photo;
            TextView name;
            TextView number;
            ImageButton call;
            ImageButton message;
            ImageButton more;
            Button modify;
            Button delete;
            LinearLayout linearlayout;
            LinearLayout calllayout;
            LinearLayout option;
            String m_number;

            String mname;
            String mnumber;

            Data data;
            private int position;
            private int position2;
            private int PERMISSION_CALL = 100;
            private int PERMISSION_SMS = 101;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                photo = itemView.findViewById(R.id.photo);
                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.number);
                call = itemView.findViewById(R.id.callbutton);
                call.setOnClickListener(this);
                message = itemView.findViewById(R.id.messagebutton);
                message.setOnClickListener(this);
                more = itemView.findViewById(R.id.morebutton);
                more.setOnClickListener(this);
                modify = itemView.findViewById(R.id.modify);
                modify.setOnClickListener(this);
                delete = itemView.findViewById(R.id.delete);
                delete.setOnClickListener(this);
                linearlayout = itemView.findViewById(R.id.linearLayout);
                calllayout = itemView.findViewById(R.id.call_layout);
                option = itemView.findViewById(R.id.moreoption);
            }

            void onBind(Data data, int position) {
                this.data = data;
                this.position = position;
                mname = data.getName();
                mnumber = data.getNumber();
                //photo.setImageResource(data.getimage());
                name.setText(data.getName());
                number.setText(data.getNumber());
                m_number = data.getNumber();
                changeVisibility(selectedItems.get(position));
                //changeVisibility2(selectedItems2.get(position));
                linearlayout.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.linearLayout:
                        m_number = number.getText().toString();
                        if (selectedItems.get(position)) {
                            // 펼쳐진 Item을 클릭 시
                            selectedItems.delete(position);
                        } else {
                            ////문제x///
                            // 직전의 클릭됐던 Item의 클릭상태를 지움
                            selectedItems.delete(preposition);
                            // 클릭한 Item의 position을 저장
                            selectedItems.put(position, true);
                        }
                        // 해당 포지션의 변화를 알림
                        if (preposition != -1) {
                            notifyItemChanged(preposition);
                        }
                        notifyItemChanged(position);//리스트 갱신을 위해 쓰인다.
                        // 클릭된 position 저장
                        preposition = position;
                        break;

                    case R.id.callbutton:
                        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL);
                        }
                        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+m_number)));
                        break;
                    case R.id.messagebutton:
                        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_SMS);
                        }
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"+m_number)));
                        break;
                    case R.id.morebutton:

                        break;

                    case R.id.modify:
                        Intent intent = new Intent(context, ModifyContactActivity.class);
                        intent.putExtra("name", mname);
                        intent.putExtra("number", mnumber);
                        activity.startActivity(intent);
                        break;

                    case R.id.delete:
                        activity.getContentResolver().delete(Uri.parse(MainActivity.PROVIDER_URI),"name="+name.getText().toString() ,null);
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
            }

            void changeVisibility2(final boolean isExpanded) {
                int dpValue = 50;
                float d = context.getResources().getDisplayMetrics().density;
                int height = (int) (dpValue * d);

                ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0); //고치
                va.setDuration(500);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        option.getLayoutParams().height = (int) valueAnimator.getAnimatedValue();
                        option.requestLayout();
                        option.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                    }
                });
                va.start();
            }
        }
    }