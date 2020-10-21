package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.SharedPrefernce.SharedPrefManager;
import com.example.prabhattradingservice.SharedPrefernce.UserData;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QueryForm extends AppCompatActivity {
    EditText username, email, query, mobileNo;
    ImageView iv_back;
    Button send;
    RequestQueue requestQueue;
    private static final int REQUEST_SIGNUP = 0;
    KProgressHUD pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_form);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();
        username = (EditText) findViewById(R.id.et_FullName);
        username.setPadding(0, 15, 0, 15);
        email = (EditText) findViewById(R.id.et_email);
        email.setPadding(0, 15, 0, 15);
        query = (EditText) findViewById(R.id.et_Query);
        query.setPadding(0, 15, 0, 15);
        mobileNo = (EditText) findViewById(R.id.et_MobileNo);
        mobileNo.setPadding(0, 15, 0, 15);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        send = (Button) findViewById(R.id.btn_Send);

        requestQueue= Volley.newRequestQueue(this);

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

               send();
            }
        });
    }

    public void send() {


        if (validate() == false) {
            onLoginFailed();
            return;
        }

        //_loginButton.setEnabled(false);

        loginByServer();
    }

    private void loginByServer() {
        pDialog = KProgressHUD.create(getApplicationContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Sending........")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

      showpDialog();


        String name = username.getText().toString();
        String emails = email.getText().toString();
        String mobile = mobileNo.getText().toString();
        String quarry = query.getText().toString();

        String url = "http://prabhattrading.com/apis/query";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
        //    hidepDialog();

                    //starting the profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Query send  Successfully ", Toast.LENGTH_SHORT).show();


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
          //  hidepDialog();
              //  Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("email", emails);
                params.put("mobile",mobile);
                params.put("query",quarry);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();

                // headers.put("Authorization", "Bearer "+Token);

                return headers;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Please fill all requirements", Toast.LENGTH_LONG).show();

        send.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String name = username.getText().toString();
        String emails = email.getText().toString();
        String mobile = mobileNo.getText().toString();
        String quarry = query.getText().toString();


        if (name.isEmpty() ) {
            username.setError("please enter the name ");
            requestFocus(username);
            valid = false;
        } else {
            username.setError(null);
        }


        if (emails.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emails).matches()) {
            email.setError("enter a valid email address");
            requestFocus(email);
            valid = false;
        } else {
            email.setError(null);
        }
        if (mobile.isEmpty() || mobile.length()<10) {
            mobileNo.setError("enter a Mobile");
            requestFocus(mobileNo);
            valid = false;
        } else {
            mobileNo.setError(null);
        }

        if (quarry.isEmpty() ) {
           query.setError("Please enter your query");
            requestFocus(query);
            valid = false;
        } else {
            query.setError(null);
        }


        return valid;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
   }