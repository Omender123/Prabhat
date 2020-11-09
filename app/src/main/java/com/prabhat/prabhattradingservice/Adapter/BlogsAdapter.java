package com.prabhat.prabhattradingservice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prabhat.prabhattradingservice.ClickListener.GalleryClickListner;
import com.prabhat.prabhattradingservice.Model.BlogsModel;
import com.prabhat.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.MyViewHolder> {
private  ArrayList<BlogsModel>blogsModels;
 private Context context;
    private GalleryClickListner galleryClickListner;
    public BlogsAdapter(ArrayList<BlogsModel> blogsModels,/* Context context,*/GalleryClickListner galleryClickListner) {
        this.blogsModels = blogsModels;
     //   this.context = context;
        this.galleryClickListner=galleryClickListner;
    }

    public BlogsAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bolgs_layout,parent,false);

        return new BlogsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       String image=blogsModels.get(position).getBlogsImage();
        String heading1= blogsModels.get(position).getH1();
        String heading2= blogsModels.get(position).getH2();
        String id=blogsModels.get(position).getId();

        Picasso.get().load(image).into(holder.BlogsImage);
        holder.h1.setText(heading1);
        holder.h2.setText(heading2);

         /*holder.seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent =new Intent(context, BlogsSeeAll.class);
                 intent.putExtra("id"," "+id);
                *//* intent.putExtra("image"," "+image);
                 intent.putExtra(" h1" ," "+heading1);
                 intent.putExtra("h2"," "+ heading2);
*//*
               // Toast.makeText(context, ""+image+heading1, Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return blogsModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView BlogsImage;
        TextView h1,h2;
        Button seeAll;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            BlogsImage =itemView.findViewById(R.id.blogs_image);
            h1=itemView.findViewById(R.id.blogs_h1);
            h2=itemView.findViewById(R.id.blogs_h2);
            seeAll=itemView.findViewById(R.id.btnSeeAll);

            seeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    galleryClickListner.onItemClickListener(getAdapterPosition());
                }
            });

        }
    }
}
