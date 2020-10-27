package com.example.prabhattradingservice.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Adapter.IndcatorAdapter;
import com.example.prabhattradingservice.Adapter.PositionalAdapter;
import com.example.prabhattradingservice.Adapter.SlidingImageAdapter;
import com.example.prabhattradingservice.Adapter.YoutubeImageAdapter;
import com.example.prabhattradingservice.CourseScreen.OfflineCourse;
import com.example.prabhattradingservice.CourseScreen.OnlineCourse;
import com.example.prabhattradingservice.Model.ImageSilderModel;
import com.example.prabhattradingservice.Model.IndicatorModal;
import com.example.prabhattradingservice.Model.PositionalModal;
import com.example.prabhattradingservice.Model.YouTubeModal;
import com.example.prabhattradingservice.Model.YoutubeImagesModel;
import com.example.prabhattradingservice.R;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment extends Fragment {

    Animation animation;
    WebView webView;
    // page Slider
    private static ViewPager mpage;
    CirclePageIndicator indicator;
    private static int currentPage=0;
    private static int No_page=0;
    ImageSilderModel ImageSilderModels;
    ArrayList<ImageSilderModel>Image=new ArrayList<>();
    ArrayList<YouTubeModal>youTubeModals;

    RecyclerView recyclerViewPrice,recyclerViewIndicator,recyclerViewPositional;
    RequestQueue requestQueue;
    YoutubeImageAdapter mRecyclerAdapter;
    ImageView online,offline;

    TextView priceSeeAll,IndicatorSeeAll,positonalSeeAll;

// Indicator Video
    ArrayList<IndicatorModal>indicatorModals=new ArrayList<>();
    IndcatorAdapter indcatorAdapter;
// Positional Video
 ArrayList<PositionalModal>positionalModals;
 PositionalAdapter positionalAdapter;
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

            positionalModals=new ArrayList<PositionalModal>();
       /* requestQueue= Volley.newRequestQueue(getContext());
*/
        positonalSeeAll=view.findViewById(R.id.positionalSeeAll);
        positonalSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),PositionalSeeall.class));
            }
        });


        priceSeeAll=view.findViewById(R.id.priceSeeAll);
        priceSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),PriceSeeAll.class));
            }
        });



        IndicatorSeeAll=view.findViewById(R.id.INDICATORSeeAll);
        IndicatorSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),IndicatorSeeAll.class));
            }
        });

        priceSeeAll  =view.findViewById(R.id.priceSeeAll);
        priceSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),PriceSeeAll.class));
            }
        });

                youTubeModals=new ArrayList<YouTubeModal>();
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
        String url="https://prabhattrading.com/apis/banner";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<=jsonArray.length();i++)
                    {
                        ImageSilderModels=new ImageSilderModel();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        final String image=jsonObject1.getString("banner_img");
                        ImageSilderModels.setImageurl(image);

                        Image.add(ImageSilderModels);

                        mpage.setAdapter(new SlidingImageAdapter(Image,getContext()));
                        indicator.setViewPager(mpage);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);
        No_page = Image.size();
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
        }, 3000, 3000);

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
      String url="http://prabhattrading.com/apis/PRICE-ACTION-STRATEGY ";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String data=jsonObject.getString("data");

                    JSONArray jsonArray=new JSONArray(data);
                    for (int i=0; i<=jsonArray.length();i++){
                        YouTubeModal modalData=new YouTubeModal();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String image=jsonObject1.getString("image");
                        String video=jsonObject1.getString("video_link");
                        modalData.setYoutubeImage(image);
                        modalData.setYoutubeVideo(video);
                        youTubeModals.add(modalData);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mRecyclerAdapter = new YoutubeImageAdapter(youTubeModals,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerViewPrice.setLayoutManager(mLayoutManager);
                recyclerViewPrice.setItemAnimator(new DefaultItemAnimator());
                recyclerViewPrice.setAdapter(mRecyclerAdapter);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
         requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    public void IndicatorBasedStrategy(){

        String url="http://prabhattrading.com/apis/INDICATOR-BASED-STRATEGY";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(getActivity(), response+"", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String data=jsonObject.getString("data");

                    JSONArray jsonArray=new JSONArray(data);
                    for (int i=0; i<=jsonArray.length();i++){
                        IndicatorModal modalData=new IndicatorModal();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String image=jsonObject1.getString("image");
                        String video=jsonObject1.getString("video_link");

                         modalData.setIndicatorImage(image);
                        modalData.setIndicatorVideo(video);

                        indicatorModals.add(modalData);
                        //Toast.makeText(getApplicationContext(), ""+jsonArray, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

               indcatorAdapter = new IndcatorAdapter(indicatorModals,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerViewIndicator.setLayoutManager(mLayoutManager);
                recyclerViewIndicator.setItemAnimator(new DefaultItemAnimator());
                recyclerViewIndicator.setAdapter(indcatorAdapter);
         }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    public void PositionalStocks(){


        String url="http://prabhattrading.com/apis/POSITIONAL-STOCKS";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(getActivity(), response+"", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String data=jsonObject.getString("data");

                    JSONArray jsonArray=new JSONArray(data);
                    for (int i=0; i<=jsonArray.length();i++){
                        PositionalModal modalData=new PositionalModal();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String image=jsonObject1.getString("image");
                        String video=jsonObject1.getString("video_link");
                      /*  String id =jsonObject1.getString("id");
                        modalData.setId(id);
                      */  modalData.setPositionalVideo(video);
                        modalData.setPositionalImage(image);
                        positionalModals.add(modalData);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                positionalAdapter = new PositionalAdapter(positionalModals,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerViewPositional.setLayoutManager(mLayoutManager);
                recyclerViewPositional.setItemAnimator(new DefaultItemAnimator());
                recyclerViewPositional.setAdapter(positionalAdapter);
        }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


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