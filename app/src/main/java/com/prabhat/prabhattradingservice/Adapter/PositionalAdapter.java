package com.prabhat.prabhattradingservice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prabhat.prabhattradingservice.Fragments.PositionalWebView;
import com.prabhat.prabhattradingservice.Model.PositionalModal;
import com.prabhat.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PositionalAdapter extends RecyclerView.Adapter<PositionalAdapter.MyViewHolder>{

    ArrayList<PositionalModal> positionalModals;
    Context context;

    public PositionalAdapter(ArrayList<PositionalModal> positionalModals, Context context) {
        this.positionalModals = positionalModals;
        this.context = context;
    }

    public PositionalAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);

        return new PositionalAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(positionalModals.get(position).getPositionalImage()).into(holder.videoImage);
        holder.videoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PositionalWebView.class);
                String Positional= positionalModals.get(position).getPositionalVideo();
                intent.putExtra("Positional",""+Positional);
                context.startActivity(intent);
               // Toast.makeText(context, ""+positionalModals.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return positionalModals.size();
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
