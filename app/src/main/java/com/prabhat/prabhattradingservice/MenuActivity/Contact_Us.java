package com.prabhat.prabhattradingservice.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.prabhat.prabhattradingservice.MainActivity;
import com.prabhat.prabhattradingservice.R;

public class Contact_Us extends AppCompatActivity {
    Animation animation;
    GridLayout gridLayout;
    ImageView iv_back;
    TextView VisitWebsite;
    ImageView facebook,youtube,twitter,telegram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
       facebook=findViewById(R.id.facebook);
        youtube=findViewById(R.id.youTube);
        twitter=findViewById(R.id.Twitter);
        telegram=findViewById(R.id.Telegram);
        gridLayout=findViewById(R.id.home_grid);
        iv_back = (ImageView) findViewById(R.id.iv_back1);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Contact_Us.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        contactWithUs();
        setSingleEvent(gridLayout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent intent = new Intent(Contact_Us.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    public void openActivity(int click){
        switch (click){
              case 3:
                  String url = "https://prabhattrading.com";
                          Intent i = new Intent(Intent.ACTION_VIEW);
                          i.setData(Uri.parse(url));
                          startActivity(i);

                  break;
        }
    }

    private void setSingleEvent(GridLayout gridLayout) {
        //loop for all child event
        for(int i=0; i<gridLayout.getChildCount(); i++){
            CardView cardView=(CardView) gridLayout.getChildAt(i);

            final int clickCardView=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
                    cardView.startAnimation(animation);
                    openActivity(clickCardView);
                    //Toast.makeText(About.this, ""+clickCardView, Toast.LENGTH_SHORT).show();
                }
            });
        }
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