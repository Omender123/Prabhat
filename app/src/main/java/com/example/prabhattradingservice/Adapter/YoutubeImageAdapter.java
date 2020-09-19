package com.example.prabhattradingservice.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.prabhattradingservice.Model.YoutubeImagesModel;
import com.example.prabhattradingservice.R;

import java.util.List;

public class YoutubeImageAdapter extends RecyclerView.Adapter<YoutubeImageAdapter.MyViewHolder> {
List<YoutubeImagesModel> youtubeVideoModels;
Context context;
DisplayMetrics displayMetrics = new DisplayMetrics();

    public YoutubeImageAdapter(List<YoutubeImagesModel> youtubeVideoModels, Context context) {
        this.youtubeVideoModels = youtubeVideoModels;
        this.context = context;
    }

    public YoutubeImageAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return youtubeVideoModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
      ImageView videoImage,playButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
                    videoImage =itemView.findViewById(R.id.videoImage);
                   // playButton=itemView.findViewById(R.id.playButton);

        }

        public void onBind(final int position) {
            final YoutubeImagesModel mYoutubeVideo = youtubeVideoModels.get(position);
            ((Activity) itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            if (mYoutubeVideo.getImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(mYoutubeVideo.getImageUrl()).
                        apply(new RequestOptions().override(width - 36, 200))
                        .into(videoImage);
            }

         /*   playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,Fullscreen.class);
                    intent.putExtra("position",""+position);
                    context.startActivity(intent);

                }
            });*/

             }


        }
    }

