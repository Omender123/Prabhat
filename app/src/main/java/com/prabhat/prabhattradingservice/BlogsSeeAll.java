package com.prabhat.prabhattradingservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prabhat.prabhattradingservice.Abouts_Activty.Blogs;
import com.squareup.picasso.Picasso;

public class BlogsSeeAll extends AppCompatActivity {
ImageView images;
TextView h1,h2;
ActionBar actionBar;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs_see_all);
         images =findViewById(R.id.blogsSeeall_image);
         h1=findViewById(R.id.blogsSeeall_h1);
         h2=findViewById(R.id.blogsSeeall_h2);

         Toast.makeText(this, ""+getIntent().getStringExtra("image"), Toast.LENGTH_SHORT).show();
         String image=getIntent().getStringExtra("img");
         Picasso.get().load("https://prabhattrading.com//wp-content//uploads//2020//04//banner3-1400x400.png").into(images);
         h1.setText(getIntent().getStringExtra("h1"));
         h2.setText(getIntent().getStringExtra("h2"));
         actionBarSetup();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* if (item.getItemId()==android.R.id.home){
            finish();

        }*/

        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(BlogsSeeAll.this, Blogs.class);
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
            actionBar.setTitle(getIntent().getStringExtra("h1"));
        }
    }
}