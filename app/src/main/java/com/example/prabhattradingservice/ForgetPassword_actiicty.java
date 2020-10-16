package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Model.MSG;
import com.example.prabhattradingservice.Retrofit.APIService;
import com.example.prabhattradingservice.Retrofit.ApiClient;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword_actiicty extends AppCompatActivity {
    private EditText email;
    KProgressHUD progressDialog;
    private static final String TAG = "Forget Password";
    RequestQueue requestQueue;
    Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_actiicty);
              email =findViewById(R.id.inputReEmail);
           Continue =findViewById(R.id.Continue);

           requestQueue= Volley.newRequestQueue(this);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPassword();
            }
        });
    }
    public void ForgetPassword() {
        Log.d(TAG, "Password send in your Register email");

        if (validate() == false) {
            onSignupFailed();
            return;
        }
        saveToServerDB();
    }

    public void onSignupSuccess() {
        Continue.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "please check Your Email", Toast.LENGTH_LONG).show();

        Continue.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String emails = email.getText().toString();



        if (emails.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emails).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        return valid;
    }

    private void saveToServerDB() {
        progressDialog=  KProgressHUD.create(ForgetPassword_actiicty.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        showpDialog();

        String emails = email.getText().toString();


        APIService service = ApiClient.getClient().create(APIService.class);
        //User user = new User(name, email, password);


        Call<MSG> forgetpassword = service.forgetPassword(emails);


        forgetpassword.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();

                if (response.isSuccessful()) {
                    Intent i = new Intent(ForgetPassword_actiicty.this, Login_Activity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getBaseContext(), "Password is send in your Registered Email", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
                Toast.makeText(getBaseContext(), ""+t.toString(), Toast.LENGTH_LONG).show();

            }
        });



    }

    private void showpDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hidepDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


}