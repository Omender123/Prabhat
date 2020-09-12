package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Verification_activity extends AppCompatActivity {
EditText otp;
TextView Resend;
Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_activity);

        verify=findViewById(R.id.btnVerify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Verification_activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}