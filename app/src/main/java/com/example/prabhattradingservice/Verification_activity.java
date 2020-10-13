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

import com.example.prabhattradingservice.Model.MSG;
import com.example.prabhattradingservice.Retrofit.APIService;
import com.example.prabhattradingservice.Retrofit.ApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification_activity extends AppCompatActivity {
 private EditText otp;
    KProgressHUD progressDialog;
    private static final String TAG = "OTP Verification ";


Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_activity);

        verify=findViewById(R.id.btnVerify);
        otp=findViewById(R.id.inputOtp);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
       /* pDialog = new ProgressDialog(RegistrationActivity.this,
                R.style.Theme_AppCompat_DayNight);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Creating Account...");
        pDialog.setCancelable(false);
        */
        progressDialog=  KProgressHUD.create(Verification_activity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setBackgroundColor(R.color.grey_light_secondary)
                .setLabel("Please Checking.....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        showpDialog();

        String code = otp.getText().toString();

        APIService service = ApiClient.getClient().create(APIService.class);
        //User user = new User(name, email, password);


        Call<MSG> userotp = service.userotp(code);

        userotp.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {

                //onSignupSuccess();

                if (response.isSuccessful()) {
                    hidepDialog();

                    if (response.body().getSuccess().equals(" 1")) {
                        Intent i = new Intent( Verification_activity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(getBaseContext(), "Registration Complete ", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "Please Enter Right Otp", Toast.LENGTH_LONG).show();


                    }

                }
               /* if (response.isSuccessful()) {
                    Intent i = new Intent( Verification_activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getBaseContext(), "Registration Complete ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Right Otp", Toast.LENGTH_LONG).show();
                }
               */ /*Log.d("onResponse", "" + response.body().getMessage());
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
                Toast.makeText(getBaseContext(), " "+t.toString(), Toast.LENGTH_LONG).show();
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