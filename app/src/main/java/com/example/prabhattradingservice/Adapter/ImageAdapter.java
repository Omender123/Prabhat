package com.example.prabhattradingservice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prabhattradingservice.Model.ModelData;
import com.example.prabhattradingservice.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class ImageAdapter extends  RecyclerView.Adapter<ImageAdapter.MyHolder> {
    ArrayList<ModelData> ImageModals;
    Context context;

    public ImageAdapter(ArrayList<ModelData> imageModals, Context context) {
        ImageModals = imageModals;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customlistview,parent,false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.firstTxt.setText(ImageModals.get(position).getFirstTxt());
        holder.secondTxt.setText(ImageModals.get(position).getSecondTxt());
        holder.image.setImageResource(ImageModals.get(position).getImages());

        //Picasso.get().load(ImageModals.get(position).getImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView firstTxt,secondTxt;
        public ImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            firstTxt=itemView.findViewById(R.id.firstTxt);
            secondTxt=itemView.findViewById(R.id.secondTxt);
            image=itemView.findViewById(R.id.imageLogo);
        }
    }
}
