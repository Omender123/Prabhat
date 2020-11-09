package com.prabhat.prabhattradingservice.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prabhat.prabhattradingservice.ClickListener.GalleryClickListner;
import com.prabhat.prabhattradingservice.Model.GalleryModalData;
import com.prabhat.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GallryAdapter extends RecyclerView.Adapter<GallryAdapter.MyHolder> {
    ArrayList<GalleryModalData>galleryModalData;

  private GalleryClickListner galleryClickListner;

    public GallryAdapter(ArrayList<GalleryModalData> galleryModalData,GalleryClickListner galleryClickListner) {
        this.galleryModalData = galleryModalData;
        this.galleryClickListner=galleryClickListner;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    galleryClickListner.onItemClickListener(getAdapterPosition());
                }
            });

        }
    }
}
