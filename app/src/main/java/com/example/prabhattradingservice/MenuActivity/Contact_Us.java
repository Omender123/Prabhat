package com.example.prabhattradingservice.MenuActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.prabhattradingservice.Fragments.Home_Fragment;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.R;

public class Contact_Us extends AppCompatActivity {
Toolbar toolbar;
TextView VisitWebsite;
Animation animation;

    ImageView imageView,facebook,youtube,twitter,telegram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        toolbar=findViewById(R.id.toolbar1);
        VisitWebsite =findViewById(R.id.txtVisitWebsite);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        facebook=findViewById(R.id.facebook);
        youtube=findViewById(R.id.youTube);
        twitter=findViewById(R.id.Twitter);
        telegram=findViewById(R.id.Telegram);


        VisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://prabhattrading.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
                VisitWebsite.startAnimation(animation);

            }
        });
        contactWithUs();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      /*  if (item.getItemId()==android.R.id.home){
            finish();

        }*/
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(Contact_Us.this, MainActivity.class);
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


        Intent intent = new Intent(Contact_Us.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
    public void contactWithUs(){
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/prabhatttrading/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
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
                animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
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
                animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
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
                animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
                twitter.startAnimation(animation);
            }
        });
    }

}