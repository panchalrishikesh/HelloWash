package com.hw.hellowash.networkcall;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by KSTL on 03-04-2017.
 */

public interface ApiInterface
{

    @FormUrlEncoded
    @POST("OAuth/Token")
    Call<JsonObject> userLogin(@Field("grant_type") String grant_type,
                               @Field("username") String username,
                               @Field("password") String password);

    @POST("/sign_in")
    Call<JsonObject> userLogin(@Body JsonObject task);

}
