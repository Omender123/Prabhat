package com.example.prabhattradingservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Model.MSG;
import com.example.prabhattradingservice.Retrofit.APIService;
import com.example.prabhattradingservice.Retrofit.ApiClient;
import com.kaopiz.kprogresshud.KProgressHUD;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class Verification_activity extends AppCompatActivity {
 private EditText otp;
    KProgressHUD progressDialog;
    private static final String TAG = "OTP Verification ";
    RequestQueue requestQueue;

Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_activity);

        verify=findViewById(R.id.btnVerify);
        otp=findViewById(R.id.inputOtp);
        requestQueue= Volley.newRequestQueue(this);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   final String code=otp.getText().toString().trim();
                Otp();
            }
        });
    }



    public void Otp() {
        Log.d(TAG, "Signup");

        if (validate() == false) {
            onSignupFailed();
            return;
        }
        saveToServerDB();
    }

    public void onSignupSuccess() {
        verify.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

        verify.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String code = otp.getText().toString();

        if (code.isEmpty()) {
          otp.setError("Please enter Your  Otp ");
            valid = false;
        } else {
            otp.setError(null);
        }


        return valid;
    }

    private void saveToServerDB() {
     progressDialog=  KProgressHUD.create(Verification_activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setBackgroundColor(R.color.grey_light_secondary)
                .setLabel("Please Checking.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        showpDialog();


        String url ="http://prabhattrading.com/apis/otp";
        String code = otp.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hidepDialog();
                Intent i = new Intent( Verification_activity.this,MainActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(getBaseContext(), "Registration Complete ", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                Toast.makeText(Verification_activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("otp",code);

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
        queue.add(stringRequest);

    /*    StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hidepDialog();
              if (! response.equalsIgnoreCase("error")){
                  Intent i = new Intent( Verification_activity.this,MainActivity.class);
                  startActivity(i);
                  finish();
                  Toast.makeText(getBaseContext(), "Registration Complete ", Toast.LENGTH_LONG).show();
              }else {
                  Toast.makeText(Verification_activity.this, "Please enter right otp", Toast.LENGTH_SHORT).show();
              }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                Toast.makeText(Verification_activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("otp",code);

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
        queue.add(stringRequest);
*/

     /* progressDialog=  KProgressHUD.create(Verification_activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setBackgroundColor(R.color.grey_light_secondary)
                .setLabel("Please Checking.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        showpDialog();
        String code = otp.getText().toString();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("otp", code);




        APIService service = ApiClient.getClient().create(APIService.class);
        //User user = new User(name, email, password);


        Call<MSG> userotps = service.userotp(parameters);

        userotps.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();
                if (response.isSuccessful()) {
                    Intent i = new Intent( Verification_activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getBaseContext(), "Registration Complete ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Right Otp", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
                Toast.makeText(getBaseContext(), " "+t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    */}

    private void showpDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hidepDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


}