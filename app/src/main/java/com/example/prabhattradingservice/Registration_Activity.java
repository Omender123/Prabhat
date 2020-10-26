package com.example.prabhattradingservice;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prabhattradingservice.Fragments.Home_Fragment;
import com.example.prabhattradingservice.Model.MSG;
import com.example.prabhattradingservice.Retrofit.APIService;
import com.example.prabhattradingservice.Retrofit.ApiClient;
import com.example.prabhattradingservice.SharedPrefernce.SharedPrefManager;
import com.example.prabhattradingservice.SharedPrefernce.UserData;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class Registration_Activity extends AppCompatActivity {
    private EditText _nameText, _emailText, _passwordText, _reEnterPasswordText, _mobileText;
    private static final String TAG = "SignupActivity";
    Button SignUp;
     KProgressHUD progressDialog;
     String url="http://prabhattrading.com/API/signup";
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

        requestQueue= Volley.newRequestQueue(this);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this,MainActivity.class));
            return;
        }
        SignUp = findViewById(R.id.btnSignUp);
        //if the user is already logged in we will directly start the profile activity
      /*  if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Home_Fragment.class));
            return;
        }*/


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      signup();

              /*  Intent i = new Intent(Registration_Activity.this, Verification_activity.class);
                startActivity(i);
                finish();
*/
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
        Toast.makeText(getBaseContext(), "Please fill all requirement ", Toast.LENGTH_LONG).show();

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
            requestFocus(_nameText);
            valid = false;
        } else {
            _nameText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            requestFocus(_emailText);
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if (mobile.isEmpty() || mobile.length()<10) {
            _mobileText.setError("enter a Mobile");
            requestFocus(_mobileText);
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            requestFocus(_passwordText);
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            requestFocus(_reEnterPasswordText);
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

    private void saveToServerDB() {

 /*pDialog = new ProgressDialog(RegistrationActivity.this,
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

        StringRequest request=new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hidepDialog();
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(response);
                    //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("data");

                        //creating a new user object
                        UserData user = new UserData(
                                userJson.getInt("id"),
                                userJson.getString("username"),
                                userJson.getString("email"),
                                userJson.getString("gender")
                        );


                    Intent i = new Intent(Registration_Activity.this, Verification_activity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getBaseContext(), "Otp Send in your Email Account ", Toast.LENGTH_LONG).show();

                    //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "All ready Registered", Toast.LENGTH_LONG).show();
                   // Toast.makeText(Registration_Activity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                Toast.makeText(getBaseContext(), "Your Email and Mobile No. is all ready exits", Toast.LENGTH_LONG).show();

                //Toast.makeText(Registration_Activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
               params.put("name",name);
                params.put("email", email);
                params.put("mobile",mobile);
                params.put("pass", password);
                params.put("re_pass", reEnterPassword);
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

       /* APIService service = ApiClient.getClient().create(APIService.class);
        //User user = new User(name, email, password);


        Call<MSG> userCall = service.userSignUp(name, email,mobile, password,reEnterPassword);


        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();

                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject =new JSONObject(String.valueOf(response));
                        JSONObject userJson = jsonObject.getJSONObject("data");
                        //creating a new user object
                        UserData user = new UserData(
                                userJson.getInt("id"),
                                userJson.getString("name"),
                                userJson.getString("email"),
                                userJson.getString("mobile")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        Intent i = new Intent(Registration_Activity.this, Verification_activity.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(getBaseContext(), "Otp Send in your Email Account ", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   *//* Intent i = new Intent(Registration_Activity.this, Verification_activity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getBaseContext(), "Otp Send in your Email Account ", Toast.LENGTH_LONG).show();
             *//*   }else{
                    Toast.makeText(getBaseContext(), "All ready Registered", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
                Toast.makeText(getBaseContext(), "Your Email and Mobile No. is all ready exits", Toast.LENGTH_LONG).show();

            }
        });
*/
    }

    private void showpDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hidepDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}