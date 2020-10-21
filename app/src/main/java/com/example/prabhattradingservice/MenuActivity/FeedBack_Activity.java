package com.example.prabhattradingservice.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.R;
import com.example.prabhattradingservice.SharedPrefernce.SharedPrefManager;
import com.example.prabhattradingservice.SharedPrefernce.UserData;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

public class FeedBack_Activity extends AppCompatActivity {
    Animation animation;
    ImageView iv_back;
    EditText feedback;
    TextView tvFeedback;
    RatingBar rbStars;
    Button btnSend;
    RequestQueue requestQueue;
    KProgressHUD pDialog;
    String Rating;
    String feedbacks;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tvFeedback = findViewById(R.id.tvFeedback);
        feedback=findViewById(R.id.enterfeedback);
        rbStars = findViewById(R.id.rbStars);
        btnSend=findViewById(R.id.btnSend);
        requestQueue= Volley.newRequestQueue(this);
        UserData user = SharedPrefManager.getInstance(this).getUser();

        feedbacks=feedback.getText().toString().trim();
        id=String.valueOf(user.getId());
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
                Rating=String.valueOf(rating);
               // Toast.makeText(FeedBack_Activity.this, ""+rating, Toast.LENGTH_SHORT).show();

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

           if (feedbacks.isEmpty()){
               feedback.setError("Please Enter Your Feedback");
               feedback.requestFocus();
           }else if(Rating.isEmpty()) {
               Toast.makeText(FeedBack_Activity.this, "Please give the rating ", Toast.LENGTH_SHORT).show();
           }else {
             /*  pDialog = KProgressHUD.create(getApplicationContext())
                       .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                       .setLabel("Sending........")
                       .setCancellable(false)
                       .setAnimationSpeed(2)
                       .setDimAmount(0.5f);
//                       .show();
                      showpDialog();
           */

               String url = "http://prabhattrading.com/apis/feedback";

               StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Toast.makeText(FeedBack_Activity.this, ""+response, Toast.LENGTH_SHORT).show();
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(FeedBack_Activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                   }
               }){
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       Map<String, String> params = new HashMap<>();
                       params.put("user_id,",id);
                       params.put("rating",Rating);
                       params.put("feedback",feedbacks);

                       return params;
                   }

                   @Override
                   public Map<String, String> getHeaders() throws AuthFailureError {
                       Map<String, String> headers = new HashMap<String, String>();

                       // headers.put("Authorization", "Bearer "+Token);

                       return headers;
                   }
               };

               RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
               queue.add(stringRequest);
           }

         // Toast.makeText(FeedBack_Activity.this, "Thank you for give Rating and Feedback "+Rating, Toast.LENGTH_SHORT).show();
      }
  });

    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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