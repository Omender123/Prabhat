package com.example.prabhattradingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.example.prabhattradingservice.Model.User;
import com.example.prabhattradingservice.R;
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

public class Login_Activity extends AppCompatActivity {
    EditText _emailText, _passwordText;
    Button _loginButton;
    TextView register, forgetpassword;
    RequestQueue requestQueue;
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    KProgressHUD pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        _loginButton = findViewById(R.id.btnSignIn);
        register = findViewById(R.id.gotoRegister);
        forgetpassword = findViewById(R.id.forgotPassword);
        requestQueue= Volley.newRequestQueue(this);

        _emailText = findViewById(R.id.inputEmail);
        _passwordText = findViewById(R.id.inputPassword);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         login();
              /* Intent i = new Intent(Login_Activity.this, MainActivity.class);
                startActivity(i);
                finish();
*/
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Registration_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, ForgetPassword_actiicty.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void login() {
        Log.d(TAG, "Login");

        if (validate() == false) {
            onLoginFailed();
            return;
        }

        //_loginButton.setEnabled(false);

        loginByServer();
    }

    private void loginByServer() {
        pDialog=  KProgressHUD.create(Login_Activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        showpDialog();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

   String url="http://prabhattrading.com/apis/signin";

      StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              hidepDialog();

              try {
                  //converting response to json object
                  JSONObject obj = new JSONObject(response);

                  //if no error in response
                       //getting the user from the response
                      JSONObject userJson = obj.getJSONObject("data");

                      //creating a new user object
                      UserData user = new UserData(
                              userJson.getInt("id"),
                              userJson.getString("name"),
                              userJson.getString("email"),
                              userJson.getString("mobile")
                      );

                      //storing the user in shared preferences
                      SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                      //starting the profile activity
                      finish();
                      startActivity(new Intent(getApplicationContext(), MainActivity.class));
                      Toast.makeText(Login_Activity.this, "Login Successfully ", Toast.LENGTH_SHORT).show();

              } catch (JSONException e) {
                  e.printStackTrace();
              //    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                  //  Toast.makeText(Login_Activity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
              }
          }

      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              hidepDialog();
              Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
             // Toast.makeText(Login_Activity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
          }
      }){

          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
              Map<String, String> params = new HashMap<>();
              params.put("email", email);
              params.put("pass", password);
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
       /* APIService service = ApiClient.getClient().create(APIService.class);

        Call<MSG> userCall = service.userLogIn(email, password);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();

                if (response.isSuccessful()) {
                    try {
                        JSONObject  jsonObject =new JSONObject(String.valueOf(response));
                        JSONObject userJson = jsonObject.getJSONObject("data");
                        //creating a new user object
                        UserData user = new UserData(
                                userJson.getInt("id"),
                                userJson.getString("name"),
                                userJson.getString("email"),
                                userJson.getString("mobile")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        Intent i = new Intent(Login_Activity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(Login_Activity.this, "Login Successfully ", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();

                        Toast.makeText(Login_Activity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                  *//*  Intent i = new Intent(Login_Activity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    Toast.makeText(Login_Activity.this, "Login Successfully ", Toast.LENGTH_SHORT).show();
           *//*     } else {

                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
                Toast.makeText(Login_Activity.this, " "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        }
*/

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

        _loginButton.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            requestFocus(_emailText);
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError("Password is empty");
            requestFocus(_passwordText);
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}