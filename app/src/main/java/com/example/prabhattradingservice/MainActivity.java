package com.example.prabhattradingservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabhattradingservice.MenuActivity.About;
import com.example.prabhattradingservice.MenuActivity.Calculator;
import com.example.prabhattradingservice.MenuActivity.Contact_Us;
import com.example.prabhattradingservice.Fragments.Home_Fragment;
import com.example.prabhattradingservice.MenuActivity.FeedBack_Activity;
import com.example.prabhattradingservice.MenuActivity.Gallery;
import com.example.prabhattradingservice.MenuActivity.Trading_Calls;
import com.example.prabhattradingservice.MenuActivity.Course;
import com.example.prabhattradingservice.SharedPrefernce.SharedPrefManager;
import com.example.prabhattradingservice.SharedPrefernce.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    FloatingActionButton message,phone;
    private View navHeader;
    TextView name,email,mobile;
    CircleImageView imgProfile,imgEdit;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* toolbar.setTitle("Prabhat Trading Service");
*/
     message=findViewById(R.id.message);
        phone=findViewById(R.id.phone);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,QueryForm.class));
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
       // toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        toggle.syncState();


        navHeader = navigationView.getHeaderView(0);
        name = (TextView) navHeader.findViewById(R.id.ProfileName);
        email= (TextView) navHeader.findViewById(R.id.profliegmail);
        mobile = (TextView) navHeader.findViewById(R.id.profileNumber);
        imgEdit = (CircleImageView) navHeader.findViewById(R.id.profileEdit);
        imgProfile = (CircleImageView) navHeader.findViewById(R.id.profileImage);

        //getting the current user
        UserData user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
       name.setText(user.getName());
       email.setText(user.getEmail());
       mobile.setText(user.getMobile());

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_Container,new Home_Fragment())

                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivty.class));
            }
        });
        //    checkConnection();
       changeStatusBarColor();

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

            case R.id.nav_Course:
                startActivity(new Intent(MainActivity.this, Course.class));
                Toast.makeText(this, "Welcome to Course", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Calculator:
                startActivity(new Intent(MainActivity.this, Calculator.class));
                Toast.makeText(this, "Welcome to Calculator", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_Youtube:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCvoi3COLPi2PB3WvlovGqeQ"));
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);
                 Toast.makeText(this, "Welcome to YouTube", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Gallery:
                startActivity(new Intent(MainActivity.this, Gallery.class));
                Toast.makeText(this, "Welcome to Gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Trading_Calls:
                startActivity(new Intent(MainActivity.this, Trading_Calls.class));
                Toast.makeText(this, "Welcome to Trading Calls", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_contact:
               startActivity(new Intent(MainActivity.this, Contact_Us.class));
               Toast.makeText(this, "Welcome to Contact Us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this, About.class));
                Toast.makeText(this, "Welcome to About_us ", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_Share_us:
                  share();
                Toast.makeText(this, "Welcome to Share", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_Feedback:
                startActivity(new Intent(MainActivity.this, FeedBack_Activity.class));
                Toast.makeText(this, "Welcome to Feedback ", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_Logout:
                AlertDialogBox();
                Toast.makeText(this, "Welcome to Logout ", Toast.LENGTH_SHORT).show();
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
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
public void share(){

    ApplicationInfo app = getApplicationContext().getApplicationInfo();
    String filePath = app.publicSourceDir;
    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    Uri uri = Uri.parse(filePath);
    sharingIntent.setType("*/*");
    sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
    sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/open?id=10Nc5BoYn4NZ_O8ae32UVQwyzdCzFxNy");
    startActivity(Intent.createChooser(sharingIntent, "Share "));
    Toast.makeText(this, "Welcome to Share Us ", Toast.LENGTH_SHORT).show();

}

    // set home check
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      /*  if (item.getItemId()==android.R.id.home){
            finish();

        }*/
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

   public void AlertDialogBox(){
//Logout

       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

       // set title
       alertDialogBuilder.setTitle("Prabhat Trading Service");

       // set dialog message
       alertDialogBuilder.setIcon(R.mipmap.application_icon);
       alertDialogBuilder
               .setMessage("Are you sure to Logout !!!!!")
               .setCancelable(false)
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       SharedPrefManager.getInstance(getApplicationContext()).logout();
                       Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();

                   }
               })
               .setNegativeButton("No",new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       Toast.makeText(MainActivity.this, "Logout Failed", Toast.LENGTH_SHORT).show();

                       dialog.cancel();
                   }
               });

       // create alert dialog
       AlertDialog alertDialog = alertDialogBuilder.create();

       // show it
       alertDialog.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}