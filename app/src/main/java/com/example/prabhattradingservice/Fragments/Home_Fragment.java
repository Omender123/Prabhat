package com.example.prabhattradingservice.Fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;

import com.example.prabhattradingservice.Adapter.SlidingImageAdapter;
import com.example.prabhattradingservice.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment extends Fragment {

    Animation animation;
    WebView webView;
    CardView onlineTraining,offlineTraining,video, gallery,payment;
    // page Slider
    private static ViewPager mpage;
    CirclePageIndicator indicator;
    private static int currentPage=0;
    private static int No_page=0;
     private ArrayList<Integer> ImageArray=new ArrayList<Integer>();


    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_home_, container, false);
      webView=view.findViewById(R.id.marqueeWebView);
        String url="file:///android_asset/marquee.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);



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
}