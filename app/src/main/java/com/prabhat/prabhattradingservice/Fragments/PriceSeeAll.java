package com.prabhat.prabhattradingservice.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prabhat.prabhattradingservice.Adapter.SeeAllAdapter;
import com.prabhat.prabhattradingservice.ClickListener.GalleryClickListner;
import com.prabhat.prabhattradingservice.MainActivity;
import com.prabhat.prabhattradingservice.Model.YouTubeModal;
import com.prabhat.prabhattradingservice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PriceSeeAll extends AppCompatActivity implements GalleryClickListner {
RecyclerView recyclerView;
    SeeAllAdapter seeAllAdapter;

    ArrayList<YouTubeModal> youTubeModals;
    RecyclerView.LayoutManager layoutManager;
    RequestQueue requestQueue;
      Toolbar toolbar;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_see_all);


        recyclerView=findViewById(R.id.priceSeeAllRecycler_view);
        youTubeModals=new ArrayList<YouTubeModal>();

    requestQueue= Volley.newRequestQueue(PriceSeeAll.this);
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

                seeAllAdapter = new SeeAllAdapter(youTubeModals,PriceSeeAll.this);
               layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(seeAllAdapter);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
actionBarSetup();
    }

    @Override
    public void onItemClickListener(int position) {
     Intent intent=new Intent(PriceSeeAll.this,PriceWebView.class);
        String Video=youTubeModals.get(position).getYoutubeVideo();
        Toast.makeText(this, ""+Video, Toast.LENGTH_SHORT).show();
        intent.putExtra("price"," "+Video);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(PriceSeeAll.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(PriceSeeAll.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Price Action Strategy ");

        }
    }
}