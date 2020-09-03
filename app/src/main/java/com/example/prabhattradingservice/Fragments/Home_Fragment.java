package com.example.prabhattradingservice.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.prabhattradingservice.Adapter.ImageAdapter;
import com.example.prabhattradingservice.Adapter.SlidingImageAdapter;
import com.example.prabhattradingservice.Model.ModelData;
import com.example.prabhattradingservice.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment extends Fragment {
    String [] name={"First","second","Third","Forth"};
    String [] name1={"First1","second1","Third1","Forth1"};


    // page Slider
    private static ViewPager mpage;
    CirclePageIndicator indicator;
    private static int currentPage=0;
    private static int No_page=0;
    private static final Integer[] Images={R.mipmap.first,R.mipmap.second,R.mipmap.thrid,R.mipmap.forth};
    private ArrayList<Integer> ImageArray=new ArrayList<Integer>();

    // recyclerView
    RecyclerView recyclerView;
    ImageAdapter recyclerViewAdapter;
    ArrayList<ModelData>ModelData;
    RecyclerView.LayoutManager layoutManager;


    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_home_, container, false);

        mpage=view.findViewById(R.id.pager);
      // recyclerView=view.findViewById(R.id.recycleImage);
       // recyclerView.setHasFixedSize(true);

        indicator = (CirclePageIndicator)view.findViewById(R.id.indicator);

        init();
       // getImage();
  return view;  }

    private void init() {
        for (int i = 0; i < Images.length; i++) {
            ImageArray.add(Images[i]);
        }
        mpage.setAdapter(new SlidingImageAdapter(ImageArray, getContext()));
        indicator.setViewPager(mpage);


        /*iconPageIndicator=getActivity().findViewById(R.id.indiactor);
        iconPageIndicator.setViewPager(mpage);
*/
        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        No_page = Images.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == No_page) {
                    currentPage = 0;
                }
                mpage.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
private void getImage(){

    layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    ModelData =new ArrayList<ModelData>();
    for (int i=0;i<name.length;i++){
        ModelData.add(new ModelData(name[i],name1[i],Images[i]));
    }
    recyclerViewAdapter=new ImageAdapter(ModelData,getContext());
    recyclerView.setAdapter(recyclerViewAdapter);
    }

}