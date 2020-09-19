package com.example.prabhattradingservice;



import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash_Screen extends AppCompatActivity {
private static  int SPLASH_SCREEN=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Screen.this,Login_Activity.class));
                finish();
            }
        },SPLASH_SCREEN);


        }
  /*  public void checkConnection(){
        ConnectivityManager manager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork= manager.getActiveNetworkInfo();
        if (null!=activeNetwork){
            if (activeNetwork.getType()== ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this, "Wifi is connected", Toast.LENGTH_SHORT).show();
            }else  if (activeNetwork.getType()== ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this, "Mobile Data is On", Toast.LENGTH_SHORT).show();
            }

        }else {
            startActivity(new Intent(getApplicationContext(),ErrorScreen.class));
            finish();
            Toast.makeText(this, "Please check Internet Connection", Toast.LENGTH_SHORT).show();
        }

}*/}