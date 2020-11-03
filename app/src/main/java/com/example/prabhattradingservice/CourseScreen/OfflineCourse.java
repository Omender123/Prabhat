package com.example.prabhattradingservice.CourseScreen;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Abouts_Activty.About_us;
import com.example.prabhattradingservice.Adapter.GallryAdapter;
import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.MenuActivity.About;
import com.example.prabhattradingservice.MenuActivity.Gallery;
import com.example.prabhattradingservice.Model.GalleryModalData;
import com.example.prabhattradingservice.R;
import com.example.prabhattradingservice.SharedPrefernce.SharedPrefManager;
import com.example.prabhattradingservice.SharedPrefernce.UserData;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfflineCourse extends AppCompatActivity implements PaymentResultListener {
ActionBar actionBar;
WebView webView;
ImageView imageView;
TextView video,rate,courses;
Button Buynow,offlineBuy1;
    String rates;
    UserData user;
    RequestQueue requestQueue;
    public String url="https://prabhattrading.com/apis/offline-course";

    private static final String TAG = OfflineCourse.class.getSimpleName();

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_course);

        Checkout.preload(getApplicationContext());

        actionBarSetup();
       webView= findViewById(R.id.offlineWebView);
        String url="file:///android_asset/OfflineCourse.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        imageView =findViewById(R.id.offlineImage);
        video=findViewById(R.id.no_video);
        rate=findViewById(R.id.course_rate);
        courses=findViewById(R.id.course_duration);
       Buynow = findViewById(R.id.offlineBuy);
        offlineBuy1=findViewById(R.id.offlineBuy1);
        //getting the current user
      user = SharedPrefManager.getInstance(this).getUser();


        Buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        offlineBuy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
      requestQueue= Volley.newRequestQueue(OfflineCourse.this);

      StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://prabhattrading.com/apis/offline-course", new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
          // Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
            try {
                  JSONObject jsonObject=new JSONObject(response);
                  String data=jsonObject.getString("data");

                  JSONArray jsonArray=new JSONArray(data);

                  for (int i=0;i<jsonArray.length();i++){
                     JSONObject jsonObject1=jsonArray.getJSONObject(i);
                     String no_of_video=jsonObject1.getString("num_video");
                      String course=jsonObject1.getString("course_dur");
                      rates=jsonObject1.getString("course_price");
                      video.setText(no_of_video);
                      courses.setText(course);
                      rate.setText("₹ "+rates+".00");
                   //   Toast.makeText(OfflineCourse.this, ""+no_of_video+course+rate, Toast.LENGTH_SHORT).show();


                  }

              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(getApplicationContext(), error.toString()+"", Toast.LENGTH_SHORT).show();
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

                Intent intent = new Intent(OfflineCourse.this, MainActivity.class);
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
            actionBar.setTitle("Offline Course Details");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(OfflineCourse.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
    public void startPayment() {
//    checkout.setKeyID("<rzp_test_KrN9yMRkKh9pwG>");
        Checkout  checkout = new Checkout();

        String name =user.getName();
        String email =user.getEmail();
        String no =user.getMobile();
        checkout.setImage(R.mipmap.ic_launcher_round);
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", name);
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", rates+"00");//pass amount in currency subunits
            options.put("prefill.email", email);
            options.put("prefill.contact",no);
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}