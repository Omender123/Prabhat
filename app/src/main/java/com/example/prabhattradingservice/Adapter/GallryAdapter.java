package com.example.prabhattradingservice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prabhattradingservice.Model.GalleryModalData;
import com.example.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class GallryAdapter extends RecyclerView.Adapter<GallryAdapter.MyHolder> {
    ArrayList<GalleryModalData>galleryModalData;
  public   Context context;

    public GallryAdapter(ArrayList<GalleryModalData> galleryModalData, Context context) {
        this.galleryModalData = galleryModalData;
        this.context=context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customview_gallery,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
       // holder.name.setText(product_modals.get(position).getProduct_name());
        Picasso.get().load(galleryModalData.get(position).getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return galleryModalData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.Gallery_image);

        }
    }
}
