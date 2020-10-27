package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Abouts_Activty.Blogs;
import com.example.prabhattradingservice.Adapter.BlogsAdapter;
import com.example.prabhattradingservice.Model.BlogsModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BlogsSeeAll extends AppCompatActivity {
ImageView images;
TextView h1,h2;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs_see_all);
         images =findViewById(R.id.blogsSeeall_image);
         h1=findViewById(R.id.blogsSeeall_h1);
         h2=findViewById(R.id.blogsSeeall_h2);

        String id=getIntent().getStringExtra("id");
        String image = getIntent().getStringExtra("image");
        String head1=getIntent().getStringExtra("h1");
        String head2=getIntent().getStringExtra("h1");

         Toast.makeText(this, ""+id+image+head1, Toast.LENGTH_SHORT).show();
/*

        // Picasso.get().load(blogsModel.getBlogsImage()).into(image);
        h1.setText(head1);
        h2.setText(head2);
*/



    }
}