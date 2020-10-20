package com.example.prabhattradingservice.Retrofit;


import com.example.prabhattradingservice.Model.MSG;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface APIService {
    @Headers({"Content-Type: multipart/form-data",
            "Accept: application/json"})
    @Multipart

    @FormUrlEncoded
    @POST("signup")
    Call<MSG> userSignUp(@Field("name") String name,
                         @Field("email") String email,
                         @Field("mobile") String mobile,
                         @Field("pass") String password,
                         @Field("re_pass") String repass);

    @FormUrlEncoded
    @POST("signin")
    Call<MSG> userLogIn(@Field("email") String email,
                        @Field("pass") String password);

    @FormUrlEncoded
    @POST("otp")
    Call<MSG> userotp(@FieldMap Map<String, String> otp);

    @FormUrlEncoded
    @POST("forget-password")
    Call<MSG> forgetPassword(@Field("email") String email);


}
