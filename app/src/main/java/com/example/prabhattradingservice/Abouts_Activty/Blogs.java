package com.example.prabhattradingservice.Abouts_Activty;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Adapter.BlogsAdapter;
import com.example.prabhattradingservice.Adapter.GallryAdapter;
import com.example.prabhattradingservice.MenuActivity.About;
import com.example.prabhattradingservice.MenuActivity.Gallery;
import com.example.prabhattradingservice.Model.BlogsModel;
import com.example.prabhattradingservice.Model.GalleryModalData;
import com.example.prabhattradingservice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Blogs extends AppCompatActivity {
ActionBar actionBar;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    BlogsAdapter blogsAdapter;
    ArrayList<BlogsModel> blogsModels;
    RecyclerView.LayoutManager layoutManager;

    String url=" https://prabhattrading.com/apis/blog ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        actionBarSetup();

        recyclerView=findViewById(R.id.BlogsRecyclerView);

        requestQueue= Volley.newRequestQueue(Blogs.this);
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
                layoutManager=new GridLayoutManager(getApplicationContext(),1);
                recyclerView.setLayoutManager( layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                blogsAdapter=new BlogsAdapter(blogsModels,Blogs.this);
                recyclerView.setAdapter(blogsAdapter);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* if (item.getItemId()==android.R.id.home){
            finish();

        }*/

        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(Blogs.this, About.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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
            actionBar.setTitle("Blogs");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Blogs.this, About.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

}