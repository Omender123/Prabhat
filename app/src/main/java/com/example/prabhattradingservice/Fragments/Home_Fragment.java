package com.example.prabhattradingservice.Fragments;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prabhattradingservice.Adapter.ImageAdapter;
import com.example.prabhattradingservice.Adapter.SimpleImageAdapter;
import com.example.prabhattradingservice.Adapter.SlidingImageAdapter;
import com.example.prabhattradingservice.Adapter.VideoAdapter;
import com.example.prabhattradingservice.LocalData;
import com.example.prabhattradingservice.Abouts_Activty.About_us;
import com.example.prabhattradingservice.MenuActivity.Gallery;
import com.example.prabhattradingservice.MenuActivity.Payment;
import com.example.prabhattradingservice.MenuActivity.Video;
import com.example.prabhattradingservice.Model.ModelData;
import com.example.prabhattradingservice.Model.YouTubeModel;
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

    // recyclerView
    RecyclerView recyclerView;
    ImageAdapter recyclerViewAdapter;
    ArrayList<ModelData>ModelData;
    RecyclerView.LayoutManager layoutManager;
 // youtube video recycler
    VideoAdapter videoAdapter;
    RecyclerView youTubeRecyclerView;
    ArrayList<YouTubeModel>youTubeModels;
    // Simple image
    SimpleImageAdapter imageAdapter;
    RecyclerView imageRecycler;
    ArrayList<YouTubeModel>imageModels;


    ImageView imageView,facebook,youtube,twitter,telegram;

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_home_, container, false);
      // About page

        //imageView=view.findViewById(R.id.aboutProfile);
        facebook=view.findViewById(R.id.facebook);
        youtube=view.findViewById(R.id.youTube);
        twitter=view.findViewById(R.id.Twitter);
        telegram=view.findViewById(R.id.Telegram);

        webView=view.findViewById(R.id.marqueeWebView);
        String url="file:///android_asset/marquee.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);


        offlineTraining=view.findViewById(R.id.OfflineCardView);
        onlineTraining=view.findViewById(R.id.OnlineCardView);
       video=view.findViewById(R.id.VideoCardView);
        gallery=view.findViewById(R.id.galleryCardView);
        payment=view.findViewById(R.id.paymentCardView);

        offlineTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        onlineTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(getContext(), Video.class);
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                video.startAnimation(animation);
             startActivity(intent);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Gallery.class);
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                gallery.startAnimation(animation);
                startActivity(intent);

            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Payment.class);
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                payment.startAnimation(animation);
                startActivity(intent);

            }
        });

/*
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), About_us.class));
                Toast.makeText(getContext(), "Welcome to about_us", Toast.LENGTH_SHORT).show();
            }
        });
*/
      // image slider
        mpage=view.findViewById(R.id.pager);
        indicator = (CirclePageIndicator)view.findViewById(R.id.indicator);
    /*    // images
        recyclerView=view.findViewById(R.id.recycleImage);
        recyclerView.setHasFixedSize(true);
       //You tube Video
        youTubeRecyclerView=view.findViewById(R.id.youTubeRecycler);
        youTubeRecyclerView.setHasFixedSize(true);
       //india,group
        imageRecycler=(RecyclerView) view.findViewById(R.id.imageRecycler1);
        imageRecycler.setHasFixedSize(true);
*/
        init();
        //getImage();
        //youTubeVideo();
        //Images();
        //contactWithUs();

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
private void getImage(){

    layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    ModelData =new ArrayList<ModelData>();
    for (int i=0;i<LocalData.name.length;i++){
        ModelData.add(new ModelData(LocalData.name[i],LocalData.name1[i],LocalData.Images[i]));
    }
    recyclerViewAdapter=new ImageAdapter(ModelData,getContext());
    recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void youTubeVideo(){

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        youTubeRecyclerView.setLayoutManager(layoutManager);
        youTubeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        youTubeModels=new ArrayList<YouTubeModel>();
        for (int i=0;i<LocalData.youtubeImages.length;i++){
            youTubeModels.add(new YouTubeModel(LocalData.youtubeImages[i]));
        }
        videoAdapter=new VideoAdapter(youTubeModels,getContext());
        youTubeRecyclerView.setAdapter(videoAdapter);
    }
    private void Images(){

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        imageRecycler.setLayoutManager(layoutManager);
        imageRecycler.setItemAnimator(new DefaultItemAnimator());
        imageModels=new ArrayList<YouTubeModel>();
        for (int i=0;i<LocalData.SimpleImages.length;i++){
            imageModels.add(new YouTubeModel(LocalData.SimpleImages[i]));
        }
        imageAdapter=new SimpleImageAdapter(imageModels,getContext());
        imageRecycler.setAdapter(imageAdapter);
    }

    public void contactWithUs(){
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/prabhatttrading/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                facebook.startAnimation(animation);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/channel/UCvoi3COLPi2PB3WvlovGqeQ?view_as=subscriber";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                youtube.startAnimation(animation);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://twitter.com/PrabhatService";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                twitter.startAnimation(animation);
            }
        });

        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://t.me/PrabhatTradingService";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
                twitter.startAnimation(animation);
            }
        });
    }
}