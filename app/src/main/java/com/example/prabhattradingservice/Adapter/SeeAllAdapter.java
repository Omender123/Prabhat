package com.example.prabhattradingservice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prabhattradingservice.ClickListener.GalleryClickListner;
import com.example.prabhattradingservice.Model.YouTubeModal;
import com.example.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SeeAllAdapter extends RecyclerView.Adapter<SeeAllAdapter.MyViewHolder>{
    ArrayList<YouTubeModal> youtubeVideoModels;
    private GalleryClickListner galleryClickListner;
    public SeeAllAdapter(ArrayList<YouTubeModal> youtubeVideoModels,GalleryClickListner galleryClickListner) {
        this.youtubeVideoModels = youtubeVideoModels;
        this.galleryClickListner=galleryClickListner;
    }

    public SeeAllAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.see_all_customlist,parent,false);

        return new SeeAllAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(youtubeVideoModels.get(position).getYoutubeImage()).into(holder.videoImage);

    }

    @Override
    public int getItemCount() {

        return youtubeVideoModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView videoImage,playButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImage =itemView.findViewById(R.id.SeeAllvideoImage);
            // playButton=itemView.findViewById(R.id.playButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    galleryClickListner.onItemClickListener(getAdapterPosition());
                }
            });
        }

}
}
