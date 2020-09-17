package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prabhattradingservice.R;

public class Login_Activity extends AppCompatActivity {
EditText email,pass;
Button signIn;
TextView register,forgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        signIn=findViewById(R.id.btnSignIn);
        register=findViewById(R.id.gotoRegister);
        forgetpassword=findViewById(R.id.forgotPassword);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login_Activity.this,Registration_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login_Activity.this,ForgetPassword_actiicty.class);
                startActivity(intent);
                finish();
            }
        });
    }
}