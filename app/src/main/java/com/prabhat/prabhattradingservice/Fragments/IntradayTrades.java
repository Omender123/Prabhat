package com.prabhat.prabhattradingservice.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prabhat.prabhattradingservice.Adapter.TradingAdapter;
import com.prabhat.prabhattradingservice.Model.BlogsModel;
import com.prabhat.prabhattradingservice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IntradayTrades extends Fragment {
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    TradingAdapter tradingAdapter;
    ArrayList<BlogsModel> blogsModels;
    RecyclerView.LayoutManager layoutManager;

    String url=" https://prabhattrading.com/apis/Intraday-trades";

    public IntradayTrades() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_intraday_trades, container, false);


        recyclerView= view.findViewById(R.id.intradayRecyclerView);

        requestQueue= Volley.newRequestQueue(getContext());

        blogsModels=new ArrayList<BlogsModel>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(getActivity(), response+"", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String data=jsonObject.getString("data");

                    JSONArray jsonArray=new JSONArray(data);
                    for (int i=0; i<=jsonArray.length();i++){
                        BlogsModel modalData=new BlogsModel();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String image=jsonObject1.getString("img");
                        String heading=jsonObject1.getString("heading");
                        String details=jsonObject1.getString("details");
                        String  id = jsonObject1.getString("id");
                        modalData.setId(id);
                        modalData.setBlogsImage(image);
                        modalData.setH1(heading);
                        modalData.setH2(details+"........");
                        blogsModels.add(modalData);
                        //Toast.makeText(getApplicationContext(), ""+jsonArray, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                layoutManager=new GridLayoutManager(getContext(),1);
                recyclerView.setLayoutManager( layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                tradingAdapter=new TradingAdapter(blogsModels,getContext());
                recyclerView.setAdapter(tradingAdapter);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);



        return  view;
    }
}