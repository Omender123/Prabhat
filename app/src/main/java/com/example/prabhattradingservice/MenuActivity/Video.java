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

import com.example.prabhattradingservice.Adapter.ImageAdapter;
import com.example.prabhattradingservice.Adapter.VideoAdapter;
import com.example.prabhattradingservice.Adapter.YouTubeVideoAdapter;
import com.example.prabhattradingservice.LocalData;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.Model.ModelData;
import com.example.prabhattradingservice.Model.YouTubeModel;
import com.example.prabhattradingservice.Model.YouTubeVideoModelData;
import com.example.prabhattradingservice.R;

import java.util.ArrayList;

public class Video extends AppCompatActivity {
ActionBar actionBar;

    RecyclerView recyclerView;
    YouTubeVideoAdapter youTubeVideoAdapter;
    ArrayList<YouTubeVideoModelData> ModelData;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        actionBarSetup();

        recyclerView=findViewById(R.id.VideoRecycleView);
        recyclerView.setHasFixedSize(true);
        youTubeVideo();


    }

    private void youTubeVideo(){

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ModelData=new ArrayList<YouTubeVideoModelData>();
        for (int i = 0; i< LocalData.youtubeImages.length; i++){
            ModelData.add(new YouTubeVideoModelData(LocalData.youtubeImages[i]));
        }
        youTubeVideoAdapter=new YouTubeVideoAdapter(ModelData,getApplicationContext());
        recyclerView.setAdapter(youTubeVideoAdapter);
    }
    // Action bar change tittle
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Video");
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

                Intent intent = new Intent(Video.this, MainActivity.class);
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

        Intent intent = new Intent(Video.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}