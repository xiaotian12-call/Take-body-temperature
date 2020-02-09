package com.me.temperature;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdpter extends RecyclerView.Adapter <MyAdpter.MyViewHolder>{
    List<temperature> allTemperature = new ArrayList<>();
    public void setAllTemperature(List<temperature> allTemperature) {
        this.allTemperature = allTemperature;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.tp_cell,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        temperature temperature = allTemperature.get(position);
        holder.textViewId.setText(String.valueOf(position+1));
        holder.textViewTime.setText(temperature.getTime());
        holder.textViewType.setText(temperature.getTp());
    }

    @Override
    public int getItemCount() {
        return allTemperature.size() ;
    }

    static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewId,textViewType,textViewMoney,textViewTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewType = itemView.findViewById(R.id.textViewTp);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }

}
