package com.example.prabhattradingservice.MenuActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.prabhattradingservice.Abouts_Activty.About_us;
import com.example.prabhattradingservice.Abouts_Activty.Blogs;
import com.example.prabhattradingservice.Abouts_Activty.Disclaimer;
import com.example.prabhattradingservice.Abouts_Activty.Privacy_Policy;
import com.example.prabhattradingservice.Abouts_Activty.Returns;
import com.example.prabhattradingservice.Abouts_Activty.Terms;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.R;

public class About extends AppCompatActivity {
    Animation animation;
    GridLayout gridLayout;
    ImageView iv_back;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_detail);

        gridLayout=findViewById(R.id.home_grid);
         iv_back = (ImageView) findViewById(R.id.iv_back);
         iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(About.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


     // set event
       setSingleEvent(gridLayout);


    }

    public void openActivity(int click){
        switch (click){
            case 0:
                Intent intent = new Intent(About.this, About_us.class);
                startActivity(intent);
                Toast.makeText(About.this, "Welcome to About Us Detail", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Intent intent1 = new Intent(About.this, Disclaimer.class);
                startActivity(intent1);
                Toast.makeText(About.this, "Welcome to Disclaimer", Toast.LENGTH_SHORT).show();

                break;

            case 2:
                Intent intent2 = new Intent(About.this, Privacy_Policy.class);
                startActivity(intent2);
                Toast.makeText(About.this, "Welcome to Privacy Policy", Toast.LENGTH_SHORT).show();

                break;
            case 3:
                Intent intent3 = new Intent(About.this, Terms.class);
                startActivity(intent3);
                Toast.makeText(About.this, "Welcome toTerms and Conditions", Toast.LENGTH_SHORT).show();

                break;

            case 4:
                Intent intent4 = new Intent(About.this, Returns.class);
                startActivity(intent4);
                Toast.makeText(About.this, "Welcome to Returns and Refunds Policy", Toast.LENGTH_SHORT).show();

                break;

            case 5:
                Intent intent5 = new Intent(About.this, Blogs.class);
                startActivity(intent5);
                Toast.makeText(About.this, "Welcome to Blogs", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(About.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }


}

// click change background color
/*if (cardView.getCardBackgroundColor().getDefaultColor()== -1)
                    {
                        // CHANGE COLOR
                        cardView.setCardBackgroundColor(Color.parseColor("#B71C1C"));
                    }else {
                        // CHANGE COLOR
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    */