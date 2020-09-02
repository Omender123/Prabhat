package com.example.prabhattradingservice.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.example.prabhattradingservice.R;

import java.util.ArrayList;

public class SlidingImageAdapter extends PagerAdapter {
  private ArrayList<Integer>Images;
  private LayoutInflater layoutInflater;
  private Context context;

    public SlidingImageAdapter(ArrayList<Integer> images, Context context) {
        Images = images;
        this.context = context;
        layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view =layoutInflater.inflate(R.layout.sliding_image,container,false);

   assert view !=null;
    final ImageView imageView=(ImageView) view.findViewById(R.id.image);

    imageView.setImageResource(Images.get(position));
    container.addView(view,0);
    return view;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Nullable
    @Override

    public  Parcelable saveState(){
        return null;
    }
}
