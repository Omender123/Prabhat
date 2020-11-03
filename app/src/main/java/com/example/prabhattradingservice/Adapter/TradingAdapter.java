package com.example.prabhattradingservice.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prabhattradingservice.Model.BlogsModel;
import com.example.prabhattradingservice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class TradingAdapter extends RecyclerView.Adapter<TradingAdapter.MyViewHolder> {
    private ArrayList<BlogsModel> blogsModels;
    private Context context;

    public TradingAdapter(ArrayList<BlogsModel> blogsModels, Context context) {
        this.blogsModels = blogsModels;
        this.context = context;
    }

    public TradingAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tradingcustom,parent,false);

        return new TradingAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        String image=blogsModels.get(position).getBlogsImage();
        String heading1= blogsModels.get(position).getH1();
        String heading2= blogsModels.get(position).getH2();
       // String id=blogsModels.get(position).getId();
        Picasso.get().load(image).into(holder.TradingImage);
        holder.h1.setText(heading1);
        holder.h2.setText(heading2);

    }

    @Override
    public int getItemCount() {
       return blogsModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView TradingImage;
        TextView h1, h2;



        @SuppressLint("WrongConstant")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TradingImage = itemView.findViewById(R.id.trading_image);
            h1 = itemView.findViewById(R.id.trading_h1);
            h2 = itemView.findViewById(R.id.trading_h2);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                h1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
                h2.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            }
        }
    }
}
