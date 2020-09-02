package com.example.prabhattradingservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.prabhattradingservice.MenuActivity.About_us;
import com.example.prabhattradingservice.MenuActivity.Contact_Us;
import com.example.prabhattradingservice.Fragments.Home_Fragment;
import com.example.prabhattradingservice.MenuActivity.Gallery;
import com.example.prabhattradingservice.MenuActivity.Payment;
import com.example.prabhattradingservice.MenuActivity.Training;
import com.example.prabhattradingservice.MenuActivity.Video;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    FloatingActionButton message,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        message=findViewById(R.id.message);
        phone=findViewById(R.id.phone);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919667709742"));
                startActivity(intent);
            }
        });
        mDrawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_Container,new Home_Fragment())

                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        checkConnection();
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_Container,new Home_Fragment())
                        .commit();
                Toast.makeText(this, "Welcome to home fragment", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_training:
                startActivity(new Intent(MainActivity.this, Training.class));
                Toast.makeText(this, "Welcome to Training", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_video:
                startActivity(new Intent(MainActivity.this, Video.class));
                Toast.makeText(this, "Welcome to Video", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Gallery:
                startActivity(new Intent(MainActivity.this, Gallery.class));
                Toast.makeText(this, "Welcome to Gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_payment:
                startActivity(new Intent(MainActivity.this, Payment.class));
                Toast.makeText(this, "Welcome to Payment Details", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_contact:
               startActivity(new Intent(MainActivity.this, Contact_Us.class));
               Toast.makeText(this, "Welcome to Contact Us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this, About_us.class));
                Toast.makeText(this, "Welcome to About_us", Toast.LENGTH_SHORT).show();

                break;



        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void checkConnection(){
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
    }
}