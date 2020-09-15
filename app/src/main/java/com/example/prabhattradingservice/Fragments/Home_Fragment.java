package com.example.prabhattradingservice.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.prabhattradingservice.Adapter.SlidingImageAdapter;
import com.example.prabhattradingservice.Adapter.YoutubeRecyclerAdapter;
import com.example.prabhattradingservice.CourseScreen.OfflineCourse;
import com.example.prabhattradingservice.CourseScreen.OnlineCourse;
import com.example.prabhattradingservice.Model.YoutubeVideo;
import com.example.prabhattradingservice.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home_Fragment extends Fragment {

    Animation animation;
    WebView webView;
    // page Slider
    private static ViewPager mpage;
    CirclePageIndicator indicator;
    private static int currentPage=0;
    private static int No_page=0;
     private ArrayList<Integer> ImageArray=new ArrayList<Integer>();

  /*  @BindView(R.id.recyclerViewFeed)*/
    RecyclerView recyclerViewPrice,recyclerViewIndicator,recyclerViewPositional;

    YoutubeRecyclerAdapter mRecyclerAdapter;
    Button online,offline;


    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_home_, container, false);
       online=view.findViewById(R.id.online);
        offline=view.findViewById(R.id.offline);
        webView=view.findViewById(R.id.marqueeWebView);
        String url="file:///android_asset/marquee.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);


                recyclerViewPrice=view.findViewById(R.id.recyclerViewPrice);
                recyclerViewIndicator=view.findViewById(R.id.recyclerViewIndicator);
                recyclerViewPositional=view.findViewById(R.id.recyclerViewPositional);
        PriceActionStrategy();
        IndicatorBasedStrategy();
        PositionalStocks();
        Course();


        // image slider
        mpage=view.findViewById(R.id.pager);
        indicator = (CirclePageIndicator)view.findViewById(R.id.indicator);
       init();

  return view;  }

    private void init() {
          final Integer[] Images={R.mipmap.first,R.mipmap.second,R.mipmap.thrid,R.mipmap.forth};
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

    public void PriceActionStrategy(){

        // You tube video
        ButterKnife.bind((Activity) getContext());
        // prepare data for list
        List<YoutubeVideo> youtubeVideos = prepareList();
        mRecyclerAdapter = new YoutubeRecyclerAdapter(youtubeVideos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPrice.setLayoutManager(mLayoutManager);
        recyclerViewPrice.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPrice.setAdapter(mRecyclerAdapter);

    }

    public void IndicatorBasedStrategy(){
// You tube video
        ButterKnife.bind((Activity) getContext());
        // prepare data for list
        List<YoutubeVideo> youtubeVideos = prepareList();
        mRecyclerAdapter = new YoutubeRecyclerAdapter(youtubeVideos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewIndicator.setLayoutManager(mLayoutManager);
        recyclerViewIndicator.setItemAnimator(new DefaultItemAnimator());
        recyclerViewIndicator.setAdapter(mRecyclerAdapter);

    }

    public void PositionalStocks(){
// You tube video
        ButterKnife.bind((Activity) getContext());
        // prepare data for list
        List<YoutubeVideo> youtubeVideos = prepareList();
        mRecyclerAdapter = new YoutubeRecyclerAdapter(youtubeVideos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPositional.setLayoutManager(mLayoutManager);
        recyclerViewPositional.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPositional.setAdapter(mRecyclerAdapter);

    }
    private List<YoutubeVideo> prepareList() {
        ArrayList mYoutubeVideo = new ArrayList();
        // add first item
        YoutubeVideo video1 = new YoutubeVideo();
        video1.setId(1l);
        video1.setImageUrl("https://i.ytimg.com/vi/rfbw6BymkxA/hqdefault.jpg?sqp=-oaymwEYCKgBEF5IVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLAu0AVNi9tgj0roTOkddpJ34OSZ4w");
       // video1.setTitle("Thugs Of Hindostan - Official Trailer | Amitabh Bachchan | Aamir Khan | Katrina Kaif | Fatima");
        video1.setVideoId("rfbw6BymkxA");
        mYoutubeVideo.add(video1);

        // add second item
        YoutubeVideo video2 = new YoutubeVideo();
        video2.setId(2l);
        video2.setImageUrl("https://i.ytimg.com/vi/y8bELQD3zy0/hqdefault.jpg?sqp=-oaymwEYCKgBEF5IVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLDQ8uZDoOsHkIoQg3WI89yYkVCH-g");
       // video2.setTitle("Colors for Children to Learning with Baby Fun Play with Color Balls Dolphin Slider Toy Set Kids Edu");
        video2.setVideoId("y8bELQD3zy0");
        mYoutubeVideo.add(video2);

        // add third item
        YoutubeVideo video3 = new YoutubeVideo();
        video3.setId(3l);
        video3.setImageUrl("https://i.ytimg.com/vi/zLw58dVq5VI/hqdefault.jpg?sqp=-oaymwEYCKgBEF5IVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLAILJhFbYqGoqXWsP41lun99Z9HpA");
       // video3.setTitle("Air Hostess Accepts Marriage Proposal Mid-Air, Airline Fires her.");
        video3.setVideoId("zLw58dVq5VI");
        mYoutubeVideo.add(video3);

        // add four item
        YoutubeVideo video4 = new YoutubeVideo();
        video4.setId(4l);
        video4.setImageUrl("https://i.ytimg.com/vi/07CKQzaI3F8/hqdefault.jpg?sqp=-oaymwEYCKgBEF5IVfKriqkDCwgBFQAAiEIYAXAB&rs=AOn4CLCBnMtEEcuk8Ada3rh0VyKEfYYs4g");
      //  video4.setTitle("EXPERIMENT Glowing 1000 degree METAL BALL vs Gunpowder (100 grams)");
        video4.setVideoId("07CKQzaI3F8");
        mYoutubeVideo.add(video4);


      /*  mYoutubeVideo.add(video1);
        mYoutubeVideo.add(video2);
        mYoutubeVideo.add(video3);
        mYoutubeVideo.add(video4);
       */ return mYoutubeVideo;
    }

    public void Course(){

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getContext(), OnlineCourse.class));
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                online.startAnimation(animation);

            }
        });

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OfflineCourse.class));
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                offline.startAnimation(animation);

            }
        });
    }

}