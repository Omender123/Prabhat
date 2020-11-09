package com.prabhat.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prabhat.prabhattradingservice.SharedPrefernce.SharedPrefManager;
import com.prabhat.prabhattradingservice.SharedPrefernce.UserData;

public class ProfileActivty extends AppCompatActivity {
    ImageView iv_back;
    TextView getname,getemail,getmobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activty);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivty.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        UserData user = SharedPrefManager.getInstance(this).getUser();

        getname=findViewById(R.id.getname);
        getemail=findViewById(R.id.getemail);
        getmobile=findViewById(R.id.getmobile);

        getname.setText(user.getName());
        getemail.setText(user.getEmail());
        getmobile.setText(user.getMobile());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ProfileActivty.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

}