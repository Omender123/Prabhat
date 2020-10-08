package com.example.prabhattradingservice;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.prabhattradingservice.Fragments.Home_Fragment;
import com.example.prabhattradingservice.Model.MSG;
import com.example.prabhattradingservice.Retrofit.APIService;
import com.example.prabhattradingservice.Retrofit.ApiClient;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Registration_Activity extends AppCompatActivity {
    private EditText _nameText, _emailText, _passwordText, _reEnterPasswordText, _mobileText;
    private static final String TAG = "SignupActivity";
    Button SignUp;
     KProgressHUD progressDialog;
    // String url="http://prabhattrading.com/API/signup";
    RequestQueue requestQueue;

    public Registration_Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        _nameText = (EditText) findViewById(R.id.inputName);
        _emailText = (EditText) findViewById(R.id.inputEmail);
        _mobileText = (EditText) findViewById(R.id.inputPhone);
        _passwordText = (EditText) findViewById(R.id.inputPassword);
        _reEnterPasswordText = (EditText) findViewById(R.id.inputRePassword);

        SignUp = findViewById(R.id.btnSignUp);
        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Home_Fragment.class));
            return;
        }


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //   signup();
            }
        });

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (validate() == false) {
            onSignupFailed();
            return;
        }
        saveToServerDB();
    }

    public void onSignupSuccess() {
        SignUp.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

        SignUp.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if (mobile.isEmpty() || mobile.length()<10) {
            _mobileText.setError("enter a Mobile");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

    private void saveToServerDB() {
       /* pDialog = new ProgressDialog(RegistrationActivity.this,
                R.style.Theme_AppCompat_DayNight);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Creating Account...");
        pDialog.setCancelable(false);
        */
       progressDialog=  KProgressHUD.create(Registration_Activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        showpDialog();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        final String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();


        APIService service = ApiClient.getClient().create(APIService.class);
        //User user = new User(name, email, password);


        Call<MSG> userCall = service.userSignUp(name, email,mobile, password,reEnterPassword);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();
                //onSignupSuccess();
                if (response.isSuccessful()) {
                    Intent i = new Intent(Registration_Activity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getBaseContext(), "Registration Successfully complete", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "If aou are all ready registered", Toast.LENGTH_LONG).show();
                }
                /*Log.d("onResponse", "" + response.body().getMessage());
                Intent intent = new Intent(Registration_Activity.this, MainActivity.class);
                     //  intent.putExtra("phoneno.",mobile);
                       startActivity(intent);
                       finish();
                Toast.makeText(getBaseContext(), "Registration Successfully complete", Toast.LENGTH_LONG).show();
           */ }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
                Toast.makeText(getBaseContext(), "All ready Registered", Toast.LENGTH_LONG).show();
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