package com.example.prabhattradingservice.Abouts_Activty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.MenuActivity.About;
import com.example.prabhattradingservice.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

public class Terms extends AppCompatActivity {
WebView webView;
ActionBar actionBar;
TextView disclaimer;
    ImageView iv_back;
TextView Terms;
    KProgressHUD progressDialog;
    RequestQueue requestQueue;
    String url="http://prabhattrading.com/apis/terms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
     /*   webView= findViewById(R.id.WebView);
      String url="file:///android_asset/TermsAndConditions.html";
      webView.getSettings().setJavaScriptEnabled(true);
      webView.loadUrl(url);

     */

        Terms=findViewById(R.id.Terms);
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        progressDialog=  KProgressHUD.create(Terms.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Loading.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        showpDialog();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hidepDialog();
             try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);
                    //getting the user from the response
                    JSONObject userJson = obj.getJSONObject("data");
                    String abouts=userJson.getString("terms");
                    Terms.setText(abouts);

                  //  Toast.makeText(Terms.this, ""+response, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            //    Toast.makeText(Terms.this, ""+response, Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                Toast.makeText(Terms.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    //    actionBarSetup();

        iv_back = (ImageView) findViewById(R.id.iv_back4);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), About.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


        //make sure you cast the web view before using loadUrl() function :

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            actionBar=getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("Terms and Conditions");
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* if (item.getItemId()==android.R.id.home){
            finish();

        }*/

        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(Terms.this, About.class);
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

        Intent intent = new Intent(Terms.this, About.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
    private void showpDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hidepDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}