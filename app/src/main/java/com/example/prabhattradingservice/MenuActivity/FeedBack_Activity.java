package com.example.prabhattradingservice.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.example.prabhattradingservice.Login_Activity;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.Model.MSG;
import com.example.prabhattradingservice.R;
import com.example.prabhattradingservice.Retrofit.APIService;
import com.example.prabhattradingservice.Retrofit.ApiClient;
import com.example.prabhattradingservice.SharedPrefernce.SharedPrefManager;
import com.example.prabhattradingservice.SharedPrefernce.UserData;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

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
    String id;
    private static final int REQUEST_Feedback = 0;
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
        id=String.valueOf(user.getId());

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0.5 || rating==1)
                {
                    tvFeedback.setText("Very Bad");
                }
                else if(rating==1.5 ||rating==2)
                {
                    tvFeedback.setText("Bad");
                }
                else if(rating==2.5 ||rating==3)
                {
                    tvFeedback.setText("Good");
                }
                else if(rating==3.5 ||rating==4)
                {
                    tvFeedback.setText("Very Good");
                }
                else if(rating==4.5 ||rating==5)
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

          feedback();
        /*  String sendFeedback = feedback.getText().toString();

          //Toast.makeText(FeedBack_Activity.this, ""+id+Rating+sendFeedback, Toast.LENGTH_SHORT).show();

          String url="http://prabhattrading.com/apis/feedback";

          StringRequest request=new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                 // hidepDialog();
                  Toast.makeText(FeedBack_Activity.this, ""+response, Toast.LENGTH_SHORT).show();
              }
              }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  hidepDialog();
                  Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                  // Toast.makeText(Login_Activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
              }
          }){

              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String, String> params = new HashMap<>();
                  params.put("user_id", id);
                  params.put("rating", Rating);
                  params.put("feedback",sendFeedback);
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
          queue.add(request);
     */ }

  });

    }

    public void feedback() {
      //  Log.d(TAG, "Login");

        if (validate() == false) {
            onLoginFailed();
            return;
        }

        //_loginButton.setEnabled(false);

        loginByServer();
    }

    private void loginByServer() {
        pDialog=  KProgressHUD.create(FeedBack_Activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Feedback Sending.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
                 showpDialog();

        String sendFeedback = feedback.getText().toString();

        //Toast.makeText(FeedBack_Activity.this, ""+id+Rating+sendFeedback, Toast.LENGTH_SHORT).show();

        String url="http://prabhattrading.com/apis/feedback";

        StringRequest request=new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             hidepDialog();
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(FeedBack_Activity.this, " FeedBack Successfully Send" +response, Toast.LENGTH_SHORT).show();
             //   Toast.makeText(FeedBack_Activity.this, ""+response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                Toast.makeText(getApplicationContext(), " Feedback All Ready Send", Toast.LENGTH_LONG).show();
                // Toast.makeText(Login_Activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", id);
                params.put("rating", Rating);
                params.put("feedback",sendFeedback);
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
        queue.add(request);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_Feedback) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Please fill all requirements", Toast.LENGTH_LONG).show();

        btnSend.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String feedbackSend= feedback.getText().toString();

        if (feedbackSend.isEmpty() ) {
            feedback.setError("Please enter feedback ");
            requestFocus(feedback);
            valid = false;
        } else {
            feedback.setError(null);
        }
        return valid;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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