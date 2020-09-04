package com.example.prabhattradingservice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prabhattradingservice.Model.YouTubeModel;
import com.example.prabhattradingservice.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
public  static  ArrayList<YouTubeModel>data;
public static Context context;

    public VideoAdapter(ArrayList<YouTubeModel>data,Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.youtubevideo,parent,false);


        return new VideoAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(data.get(position).getYouTubeImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
       imageView=itemView.findViewById(R.id.youTube);
        }
    }
}
