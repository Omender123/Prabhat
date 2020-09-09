package com.example.prabhattradingservice.MenuActivity;

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

import com.example.prabhattradingservice.Adapter.SimpleImageAdapter;
import com.example.prabhattradingservice.LocalData;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.Model.YouTubeModel;
import com.example.prabhattradingservice.R;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {
    SimpleImageAdapter imageAdapter;
    RecyclerView imageRecycler;
    ArrayList<YouTubeModel>imageModels;
    RecyclerView.LayoutManager layoutManager;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
         imageRecycler=findViewById(R.id.galleryRecyclerView);
        Images();
        actionBarSetup();
    }

    private void Images(){

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        imageRecycler.setLayoutManager(layoutManager);
        imageRecycler.setItemAnimator(new DefaultItemAnimator());
        imageModels=new ArrayList<YouTubeModel>();
        for (int i = 0; i< LocalData.SimpleImages.length; i++){
            imageModels.add(new YouTubeModel(LocalData.SimpleImages[i]));
        }
        imageAdapter=new SimpleImageAdapter(imageModels,getApplicationContext());
        imageRecycler.setAdapter(imageAdapter);
    }

    // Action bar change tittle
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
      /*  if (item.getItemId()==android.R.id.home){
            finish();

        }*/
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Gallery.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}