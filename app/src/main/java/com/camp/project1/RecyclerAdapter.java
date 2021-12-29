package com.camp.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Data> temp_data = new ArrayList<>();
    @NotNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false); // 만든 xml을 inflate시키는 동작.
        //새로운 view를 만들어서 view holder에 넣어주기 위함이다. why? view(view)는 xml로 이루어져있고 viewholder가 관리한다. adapter가 필요할 때마다 사용한다.
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapter.ViewHolder holder, int position) {
        //item을 하나하나 보여주는 함수???
        holder.onBind(temp_data.get(position));
    }

    @Override
    public int getItemCount() {//recyclerview의 총 개수
        return temp_data.size();
    }

    public void additem(Data data){ temp_data.add(data);
    }
    /*
    public void setnewList(ArrayList<Data> list){
        this.temp_data = list;
        notifyDataSetChanged();
    }

     */

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView name;
        TextView number;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
        }

        void onBind(Data data) {
            //photo.setImageResource(data.getimage());
            name.setText(data.getName());
            number.setText(data.getNumber());
        }
    }


}
