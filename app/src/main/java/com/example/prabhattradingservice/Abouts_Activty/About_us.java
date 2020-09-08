package com.example.prabhattradingservice.Abouts_Activty;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.prabhattradingservice.MainActivity;
import com.example.prabhattradingservice.MenuActivity.About;
import com.example.prabhattradingservice.R;

public class About_us extends AppCompatActivity {
    ActionBar actionBar;
    TextView firstTxt;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        actionBarSetup();

        firstTxt = findViewById(R.id.firstTxt1);
        String text = getResources().getString(R.string.first);
        SpannableString ss = new SpannableString(text);
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpan1 = new StyleSpan(Typeface.NORMAL);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.BOLD_ITALIC);
        // UnderlineSpan underlineSpan=new UnderlineSpan();
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan fcsOrange = new ForegroundColorSpan(Color.parseColor("#f5821f"));
        ForegroundColorSpan fcsOrange1 = new ForegroundColorSpan(Color.parseColor("#f8a45d"));
        ForegroundColorSpan fcsOrange2 = new ForegroundColorSpan(Color.parseColor("#f8a45d"));

        ss.setSpan(fcsOrange, 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsOrange1, 137, 144, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsOrange2, 254, 277, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(styleSpan, 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(styleSpan1, 137, 144, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(styleSpan2, 254, 277, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* if (item.getItemId()==android.R.id.home){
            finish();

        }*/

        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(About_us.this, About.class);
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
            actionBar.setTitle("About Us Details");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(About_us.this, About.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}