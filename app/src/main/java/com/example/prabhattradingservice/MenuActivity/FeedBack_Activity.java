package com.example.prabhattradingservice.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.R;

public class FeedBack_Activity extends AppCompatActivity {
    Animation animation;
    ImageView iv_back;
    TextView tvFeedback;
    RatingBar rbStars;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tvFeedback = findViewById(R.id.tvFeedback);
        rbStars = findViewById(R.id.rbStars);
        btnSend=findViewById(R.id.btnSend);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==1)
                {
                    tvFeedback.setText("Very Bad");
                }
                else if(rating==2)
                {
                    tvFeedback.setText("Bad");
                }
                else if(rating==3)
                {
                    tvFeedback.setText("Good");
                }
                else if(rating==4)
                {
                    tvFeedback.setText("Very Good");
                }
                else if(rating==5)
                {
                    tvFeedback.setText("Excellent");
                }
                else
                {

                }
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FeedBack_Activity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

  btnSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
          btnSend.startAnimation(animation);
          Toast.makeText(FeedBack_Activity.this, "Thank you for give Rating and Feedback ", Toast.LENGTH_SHORT).show();
      }
  });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(FeedBack_Activity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}