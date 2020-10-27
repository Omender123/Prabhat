package com.example.prabhattradingservice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prabhattradingservice.Fragments.IndicatorWebView;
import com.example.prabhattradingservice.Fragments.PriceWebView;
import com.example.prabhattradingservice.Model.IndicatorModal;
import com.example.prabhattradingservice.Model.YouTubeModal;
import com.example.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IndcatorAdapter extends RecyclerView.Adapter<IndcatorAdapter.MyViewHolder> {

    ArrayList<IndicatorModal> indicatorModals;
    Context context;

    public IndcatorAdapter(ArrayList<IndicatorModal> indicatorModals, Context context) {
        this.indicatorModals = indicatorModals;
        this.context = context;
    }

    public IndcatorAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);

        return new IndcatorAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(indicatorModals.get(position).getIndicatorImage()).into(holder.videoImage);
        holder.videoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, IndicatorWebView.class);
                String Indicator= indicatorModals.get(position).getIndicatorVideo();
                intent.putExtra("Indicator",""+Indicator);
                context.startActivity(intent);
           //     Toast.makeText(context, ""+indicatorModals.get(position).getIndicatorVideo(), Toast.LENGTH_SHORT).show();
            }
        });

        //    holder.onBind(position);
    }



    @Override
    public int getItemCount() {

        return indicatorModals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView videoImage,playButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImage =itemView.findViewById(R.id.videoImage);
            // playButton=itemView.findViewById(R.id.playButton);

        }
}
}

