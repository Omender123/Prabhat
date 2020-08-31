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
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.prabhattradingservice.Fragments.About_us_fragment;
import com.example.prabhattradingservice.Fragments.Contact_us_fragment;
import com.example.prabhattradingservice.Fragments.Gallery_fragment;
import com.example.prabhattradingservice.Fragments.Home_Fragment;
import com.example.prabhattradingservice.Fragments.Payment_fragment;
import com.example.prabhattradingservice.Fragments.Training_fragment;
import com.example.prabhattradingservice.Fragments.Video_fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

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
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_Container,new Training_fragment())
                        .commit();
                Toast.makeText(this, "Welcome to Training", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_video:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_Container,new Video_fragment())
                        .commit();
                Toast.makeText(this, "Welcome to Video", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Gallery:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_Container,new Gallery_fragment())
                        .commit();
                Toast.makeText(this, "Welcome to Gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_payment:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_Container,new Payment_fragment())
                        .commit();
                Toast.makeText(this, "Welcome to Payment Details", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_Container,new Contact_us_fragment())
                        .commit();
                Toast.makeText(this, "Welcome to Contact Us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_Container,new About_us_fragment())
                        .commit();
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
            Toast.makeText(this, "Please check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}