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

import com.example.prabhattradingservice.Model.MSG;
import com.example.prabhattradingservice.R;
import com.example.prabhattradingservice.Retrofit.APIService;
import com.example.prabhattradingservice.Retrofit.ApiClient;
import com.kaopiz.kprogresshud.KProgressHUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {
    EditText _emailText, _passwordText;
    Button _loginButton;
    TextView register, forgetpassword;
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

        _emailText = findViewById(R.id.inputEmail);
        _passwordText = findViewById(R.id.inputPassword);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 login();
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


        APIService service = ApiClient.getClient().create(APIService.class);

        Call<MSG> userCall = service.userLogIn(email, password);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();

                if (response.isSuccessful()) {
                     Intent i = new Intent(Login_Activity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    Toast.makeText(Login_Activity.this, "Login Successfully ", Toast.LENGTH_SHORT).show();
                } else {

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
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

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