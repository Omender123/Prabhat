package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class QueryForm extends AppCompatActivity {
    EditText username, email, query,mobileNo;
    ImageView iv_back;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_form);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();
        username = (EditText) findViewById(R.id.et_FullName);
        username.setPadding(0,15,0,15);
        email = (EditText) findViewById(R.id.et_email);
        email.setPadding(0,15,0,15);
         query= (EditText) findViewById(R.id.et_Query);
        query.setPadding(0,15,0,15);
        mobileNo=(EditText) findViewById(R.id.et_MobileNo);
        mobileNo.setPadding(0,15,0,15);
         iv_back = (ImageView) findViewById(R.id.iv_back);
        send = (Button) findViewById(R.id.btn_Send);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QueryForm.this, MainActivity.class));
                finish();
            }
        });

       send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
   }