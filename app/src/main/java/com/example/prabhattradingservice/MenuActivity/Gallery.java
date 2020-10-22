package com.example.prabhattradingservice.MenuActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Adapter.GallryAdapter;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.Model.GalleryModalData;
import com.example.prabhattradingservice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Gallery extends AppCompatActivity {
    ImageView iv_back;
    public String url="http://prabhattrading.com/apis/gallery/";
    RecyclerView recyclerView;
    RequestQueue requestQueue;

    GallryAdapter gallryAdapter;
    ArrayList<GalleryModalData> galleryModalData;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        recyclerView=findViewById(R.id.recycler_view_gallery);
        galleryModalData=new ArrayList<GalleryModalData>();
        getData();
        iv_back = (ImageView) findViewById(R.id.iv_back4);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Gallery.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getData(){
        requestQueue= Volley.newRequestQueue(Gallery.this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(getActivity(), response+"", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String data=jsonObject.getString("data");

                    JSONArray jsonArray=new JSONArray(data);
                    for (int i=0; i<=jsonArray.length();i++){
                       GalleryModalData modalData=new GalleryModalData();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String image=jsonObject1.getString("img");
                     //   String id=jsonObject1.getString("id");
                    //    modalData.setProduct_name(id);
                        modalData.setImage(image);
                        galleryModalData.add(modalData);
                        //Toast.makeText(getApplicationContext(), ""+jsonArray, Toast.LENGTH_SHORT).show();
                    }
                   /*
                   String image="http://www.thefastandfresh.online/upload/"+jsonObject.getString("cimage");
                   id=jsonObject.getInt("cid");
                   modalData.setProduct_name(name);
                   modalData.setId(id);
                   modalData.setProduct_image(image);
                   product_modal1.add(id);


               }
*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                layoutManager=new GridLayoutManager(getApplicationContext(),2);
                recyclerView.setLayoutManager( layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
               gallryAdapter=new GallryAdapter(galleryModalData,getApplicationContext());
                recyclerView.setAdapter(gallryAdapter);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    /*// Action bar change tittle
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Gallery");
        }
    }
    // set home check
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      *//*  if (item.getItemId()==android.R.id.home){
            finish();

        }*//*
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(Gallery.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Gallery.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}