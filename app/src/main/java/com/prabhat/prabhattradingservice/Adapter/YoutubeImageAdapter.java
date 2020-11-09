package com.prabhat.prabhattradingservice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prabhat.prabhattradingservice.Fragments.PriceWebView;
import com.prabhat.prabhattradingservice.Model.YouTubeModal;
import com.prabhat.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YoutubeImageAdapter extends RecyclerView.Adapter<YoutubeImageAdapter.MyViewHolder> {
List<YouTubeModal> youtubeVideoModels;
Context context;
DisplayMetrics displayMetrics = new DisplayMetrics();

    public YoutubeImageAdapter(List<YouTubeModal> youtubeVideoModels, Context context) {
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

        Picasso.get().load(youtubeVideoModels.get(position).getYoutubeImage()).into(holder.videoImage);
        holder.videoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(context, PriceWebView.class);
                 String price= youtubeVideoModels.get(position).getYoutubeVideo();
                 intent.putExtra("price",""+price);
                 context.startActivity(intent);
             //  Toast.makeText(context, ""+youtubeVideoModels.get(position).getYoutubeVideo(), Toast.LENGTH_SHORT).show();
            }
        });

    //    holder.onBind(position);
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

      /*  public void onBind(final int position) {
            final YoutubeImagesModel mYoutubeVideo = youtubeVideoModels.get(position);
            ((Activity) itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            if (mYoutubeVideo.getImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(mYoutubeVideo.getImageUrl()).
                        apply(new RequestOptions().override(width - 36, 200))
                        .into(videoImage);
            }

         *//*   playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,Fullscreen.class);
                    intent.putExtra("position",""+position);
                    context.startActivity(intent);

                }
            });*//*

             }
*/

        }
    }

