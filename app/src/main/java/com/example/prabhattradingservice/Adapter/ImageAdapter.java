package com.example.prabhattradingservice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prabhattradingservice.Model.ModelData;
import com.example.prabhattradingservice.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class ImageAdapter extends  RecyclerView.Adapter<ImageAdapter.MyHolder> {
    ArrayList<ModelData> ImageModals;
    Context context;

    public ImageAdapter(ArrayList<ModelData> imageModals, Context context) {
        this.ImageModals = imageModals;
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
        final Animation[] animation = new Animation[1];

        holder.firstTxt.setText(ImageModals.get(position).getFirstTxt());
        holder.secondTxt.setText(ImageModals.get(position).getSecondTxt());
        holder.image.setImageResource(ImageModals.get(position).getImages());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation[0] = AnimationUtils.loadAnimation(context,R.anim.animation);
                holder.cardView.startAnimation(animation[0]);
            }
        });

        //Picasso.get().load(ImageModals.get(position).getImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return ImageModals.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView firstTxt,secondTxt;
        public ImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            firstTxt=itemView.findViewById(R.id.firstTxt);
            secondTxt=itemView.findViewById(R.id.secondTxt);
            image=itemView.findViewById(R.id.imageLogo);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
