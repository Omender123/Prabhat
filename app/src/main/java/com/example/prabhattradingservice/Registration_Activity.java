package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration_Activity extends AppCompatActivity {
EditText name,email,phoneNo,pass,Cpass;
Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);



        SignUp=findViewById(R.id.btnSignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Registration_Activity.this,Verification_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}