package com.example.prabhattradingservice.Retrofit;


import com.example.prabhattradingservice.Model.MSG;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

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
    Call<MSG> userotp(@Field("otp") String otp);

}