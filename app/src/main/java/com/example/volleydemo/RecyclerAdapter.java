package com.example.volleydemo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.volleydemo.model.Sirfollowers;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
   Activity context;
   List<Sirfollowers> list;

    public RecyclerAdapter(Activity context, List<Sirfollowers> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custome_recyclerview, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("List", list.get(position).toString());
        Sirfollowers sirfollowers = list.get(position);
        holder.tv.setText(sirfollowers.getLogin());
        holder.tv2.setText(sirfollowers.getId()+"");
        Glide.with(context).load(sirfollowers.getAvatarUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv,tv2;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.name);
            tv2 = itemView.findViewById(R.id.id);
            img = itemView.findViewById(R.id.avtar);
        }
    }
}
